package v2;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TickThreadFactory implements ThreadFactory {
    private volatile AtomicInteger nums = new AtomicInteger();
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "tick-thread-" + nums.incrementAndGet());
        //thread.setDaemon(false);
        return thread;
    }
}
