package xyz.liangwh.headwaters.core;

import com.mysql.jdbc.Buffer;
import com.mysql.jdbc.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.liangwh.headwaters.core.dao.HwMarkDao;
import xyz.liangwh.headwaters.core.interfaces.IDGenerator;
import xyz.liangwh.headwaters.core.interfaces.NeadInit;
import xyz.liangwh.headwaters.core.model.Bucket;
import xyz.liangwh.headwaters.core.model.BucketBuffer;
import xyz.liangwh.headwaters.core.model.HeadwatersPo;
import xyz.liangwh.headwaters.core.model.HwMarkSamplePo;
import xyz.liangwh.headwaters.core.model.Result;
import xyz.liangwh.headwaters.core.utils.IdUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HeadwatersImpl implements IDGenerator, NeadInit {
    /**
     * cache 未初始化完成标识
     */
    public static final int RESULT_OK = 200;
    /**
     * cache 未初始化完成标识
     */
    public static final int EXCEPTION_ID_CACHE_INIT_FALSE = -101;
    /**
     * key不存在标识
     */
    public static final int EXCEPTION_ID_KEY_NOT_EXISTS = 404;
    /**
     * 两个桶都为初始化完成
     */
    public static final  int EXCEPTION_ID_BOTH_BUCKET_NULL = -202;
    /**
     * 最大步长
     */
    private static final int MAX_STEP = 1000000;
    /**
     * 一个BUCKET使用的持续时间，用于修改动态步长
     */
    private static final long BUCKET_DURATION = 10*50*1000L;

    private volatile boolean initStatus = false;

    private Map<String, BucketBuffer> cache = new ConcurrentHashMap<>();
    @Autowired
    private HwMarkDao hwMarkDao;

    private ExecutorService service = new ThreadPoolExecutor(
            5,
            10,
            120L,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new UpdateHeadwaterTheadFactory());

    public static class UpdateHeadwaterTheadFactory implements ThreadFactory{
        private static AtomicInteger times = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Thread-Update-Headwaters-Bucket-"+times.incrementAndGet());
        }
    }
    public static class UpdateRegularlyTheadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "Thread-Regularly-Update-Headwaters-Bucket");
            thread.setDaemon(true);
            return thread;
        }
    }



    @PostConstruct
    @Override
    public void init() {
        log.info("init...");
        updateCache();
        this.initStatus = true;
        //定时更新
         updateRegularly();
    }
    private void updateRegularly(){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(new UpdateRegularlyTheadFactory());
        service.scheduleWithFixedDelay(()->{updateCache();},60,60,TimeUnit.SECONDS);
    }
    private void updateCache(){
        log.info("update cache from db hw_mark");
        StopWatch stopWatch = new Slf4JStopWatch();
        try {
            List<HwMarkSamplePo> dbHws = hwMarkDao.getAllKeyMap();
            if(dbHws==null||dbHws.isEmpty()){
                return;
            }
            List<String> dbList = dbHws.stream().map(HwMarkSamplePo::getKey).collect(Collectors.toList());
            List<String> cacheKeys = new ArrayList<>(cache.keySet());
            Set<String> addSet = dbHws.stream().map(HwMarkSamplePo::getKey).collect(Collectors.toSet());
            Set<String> removeSet = new HashSet<>(cache.keySet());
            Map<String,Integer> keyIdMap = new HashMap<>();
            dbHws.stream().forEach(o->{
                keyIdMap.put(o.getKey(),o.getId());
            });

            String tmp ;
            for(int i=0;i<cacheKeys.size();i++){
                tmp = cacheKeys.get(i);
                if(addSet.contains(tmp)){
                    addSet.remove(tmp);
                }
            }
            for (String key:addSet){
                BucketBuffer buffer = new BucketBuffer();
                buffer.setId(keyIdMap.get(key));
                buffer.setKey(key);
                cache.put(key, buffer);
                log.info("[updateCache] add buckerbuffer {}",buffer);
            }
            for(int i=0;i<dbList.size();i++){
                tmp = dbList.get(i);
                if(removeSet.contains(tmp)){
                    removeSet.remove(tmp);
                }
            }
            for(String key:removeSet){
                cache.remove(key);
                log.info("[updateCache] remove buckerbuffer key:{}",key);

            }
        }catch (Exception e){
            log.error("update cache from db hw_mark faild!",e);
        }finally {
            stopWatch.stop("updateCache");
        }

    }


    @Override
    public Result getId(final String key) {
        if(!this.initStatus){
            return new Result(EXCEPTION_ID_CACHE_INIT_FALSE,null);
        }
        if(cache.containsKey(key)){
            BucketBuffer bb = cache.get(key);
            if(!bb.isInitStatus()){
                synchronized (bb){
                    if(!bb.isInitStatus()){
                        try {
                            updateBucket(key,bb.getCurrent());
                            log.info("init BucketBuffer,update key {} {}",key,bb.getCurrent());
                            bb.setInitStatus(true);
                        }catch (Exception e){
                            log.error("init BucketBuffer faild,key = {}",key,e);
                        }
                    }
                }
            }
            return getIdFromBucketBuffer(key);
        }else {
            return new Result(EXCEPTION_ID_KEY_NOT_EXISTS,null);
        }

    }

    private Result getIdFromBucketBuffer(final String key){
        Result res = null;
        final BucketBuffer bb = cache.get(key);
        while(true){
            bb.getLock().readLock().lock();
            try {
                final Bucket bucket = bb.getCurrent();
                //异步初始化刷新备用水桶
                if((!bb.isNextReady())
                        &&(bucket.getIdle()<0.8*bucket.getStep())
                        &&bb.getBackupThreadRunning().compareAndSet(false,true)
                ){
                    service.execute(()->{
                        Bucket next = bb.getBuckets()[bb.getNextIndex()];
                        boolean flag = false;
                        try {
                            updateBucket(bb.getKey(),next);
                            flag = true;
                        }catch (Exception e){
                            log.error("异步更新[{}]失败",key);
                        }finally {
                            if(flag){
                                bb.getLock().writeLock().lock();
                                bb.setNextReady(true);
                                bb.getBackupThreadRunning().set(false);
                                bb.getLock().writeLock().unlock();
                            }else {
                                bb.getBackupThreadRunning().set(false);
                            }
                        }
                    });
                }
                long value = IdUtils.makeTrueId(bb.getId(),bucket.getValue().getAndIncrement());
                if(value< bucket.getMax()){
                    return  new Result(RESULT_OK, value);
                }
            }finally {
                bb.getLock().readLock().unlock();
            }
            //上方获取不到id，表示id消费的太快了，等待异步线程初始化
            waitSomeTime(bb);
            bb.getLock().writeLock().lock();
            try {
                final Bucket bucket = bb.getCurrent();
                long value = IdUtils.makeTrueId(bb.getId(),bucket.getValue().getAndIncrement());
                if(value< bucket.getMax()){
                    return  new Result(RESULT_OK, value);
                }
                if(bb.isNextReady()){
                    bb.checkoutCurrent();
                    bb.setNextReady(false);
                }else{
                    log.error("boths bucket in {} are not ready to use!",bb.getKey());
                    return new Result(EXCEPTION_ID_BOTH_BUCKET_NULL,null);
                }
            }finally {
                bb.getLock().writeLock().unlock();
            }
        }

    }

    private void  waitSomeTime(BucketBuffer bb){
        int times = 0;
        while (bb.getBackupThreadRunning().get())
        {
            times++;
            if(times<3000){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    log.warn("Thread {} Interrupted",Thread.currentThread().getName());
                    break;
                }
            }else if(times<10000){
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    log.warn("Thread {} Interrupted",Thread.currentThread().getName());
                    break;
                }
            }else{
                break;
            }
        }
    }

    private void updateBucket (final String key,Bucket bucket) throws Exception{
        StopWatch stopWatch = new Slf4JStopWatch();
        try {
            BucketBuffer bb = bucket.getParent();
            HeadwatersPo po;
            if(!bb.isInitStatus()){
                po = hwMarkDao.updateAndGetHeadwaters(key);
                bb.setStep(po.getStep());
                bb.setAutoStep(po.getStep());
                bb.setUpdateTs(System.currentTimeMillis());
            }else {
                long duration = System.currentTimeMillis() - bb.getUpdateTs();
                int autoStep = bb.getAutoStep();
                if(duration<BUCKET_DURATION){
                    if((autoStep<<1)<=MAX_STEP){
                        autoStep<<=1;
                    }
                }else{
                    if((autoStep>>1)>=bb.getStep()){
                        autoStep>>=1;
                    }
                }
                log.info("key[{}],step[{}],autoStep[{}],duration[{}ms]",key,bb.getStep(),autoStep,duration);
                bb.setAutoStep(autoStep);
                po = hwMarkDao.updateAutoAndGetHeadwaters(key, autoStep);
                bb.setUpdateTs(System.currentTimeMillis());
                bb.setStep(po.getStep());
                bb.setId(po.getId());
            }
            int value = po.getInsideId() - bb.getAutoStep()+1;
            bucket.getValue().set(value);
            bucket.setInside(po.getInsideId());
            bucket.setMax(IdUtils.makeTrueId(po.getId(),bucket.getInside()));
            bucket.setStep(bb.getAutoStep());
        }catch (Exception e){
            throw  e;
        }finally {
            stopWatch.stop("updateBucket");
        }
    }
}
