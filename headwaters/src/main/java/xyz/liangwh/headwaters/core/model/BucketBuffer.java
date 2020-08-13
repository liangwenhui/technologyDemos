package xyz.liangwh.headwaters.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@Data
public class BucketBuffer {
    private int id;
    private String key;
    private Bucket[] buckets = new Bucket[2];
    private volatile int currentBucket = 0;//当前使用中的bucket下标
    private volatile boolean nextReady = false;//备用bucket是否可用
    private volatile boolean initStatus = false;//是否完成初始化
    //异步线程是否运行中
    private final AtomicBoolean backupThreadRunning = new AtomicBoolean(false);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private volatile int step;
    private volatile int autoStep;
    private volatile long updateTs=0;

    public BucketBuffer(){

        Bucket b1 = new Bucket();
        b1.setParent(this);
        Bucket b2 = new Bucket();
        b2.setParent(this);
        buckets[0] = b1;
        buckets[1] = b2;
    }

    public Bucket getCurrent(){
        return buckets[currentBucket];
    }

    public int getNextIndex(){
        return (currentBucket+1) % 2;
    }

    public void checkoutCurrent(){
        currentBucket = getNextIndex();
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("").append(this.id);
        sb.append("").append(this.initStatus);
        sb.append("").append(this.updateTs);
        sb.append("").append(this.key);
        sb.append("").append(this.autoStep);
        sb.append("").append(this.step);
        sb.append("}");
        return sb.toString();

    }

}
