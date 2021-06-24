package xyz.liangwh.io.socket.demo1;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 管理Loop的组
 */
public class LwhEventGroup {
    private  AtomicInteger index = new AtomicInteger(0);
    private LwhEventLoop[] loops;
    @SneakyThrows
    public LwhEventGroup (int num){
        loops = new LwhEventLoop[num];
        for(int i=0;i<num;i++){
            loops[i] = new LwhEventLoop("worker_"+i);
        }
    }
    //轮询
    public LwhEventLoop ChoseLoop(){
        return loops[index.incrementAndGet()%loops.length];
    }

}
