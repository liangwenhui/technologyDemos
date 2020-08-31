package v1;

import com.lmax.disruptor.AlertException;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException, TimeoutException, AlertException, IOException {
        class LwhThreadFactory implements ThreadFactory{
            private volatile AtomicInteger nums = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "lwh-thread-" + nums.incrementAndGet());
                //thread.setDaemon(false);
                return thread;
            }
        }

        int bufferSize = 16;

        Disruptor<LongEvent> disruptor =
                new Disruptor<LongEvent>(
                        new LongEventFactory(),
                        bufferSize,
                        new LwhThreadFactory()
                );
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.handleEventsWith(new LongEventHandler());
        //disruptor.handleEventsWithWorkerPool(new LongEventWorkHandle());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        for(int i=0;i<2;i++){
            long sequence = ringBuffer.next();
            try {
                LongEvent event = ringBuffer.get(sequence);
                event.setValue(i);
            }finally {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }



    }
}
