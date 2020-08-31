package v1;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ThreadFactory;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"lwh-thread");
            }
        };
        int bufferSize = 16;

        Disruptor<LongEvent> disruptor =
                new Disruptor<LongEvent>(
                        new LongEventFactory(),
                        bufferSize,
                        threadFactory
                );
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        for(int i=0;true;i++){
            long sequence = ringBuffer.next();
            try {
                LongEvent event = ringBuffer.get(sequence);
                event.setValue(i);
            }finally {
                ringBuffer.publish(sequence);
            }
            //Thread.sleep(10);
        }

    }
}
