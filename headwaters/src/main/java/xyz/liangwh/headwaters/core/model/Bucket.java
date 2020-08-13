package xyz.liangwh.headwaters.core.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
@Data
public class Bucket {
    private AtomicInteger value = new AtomicInteger(0);
    private long max = 0;
    private int inside = 0;
    private int step = 0;
    //可指向buffer
    private BucketBuffer parent;

    public int getIdle(){
        return this.inside - value.get();
    }
}
