package v2;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class WorkerMain {

    /**
     * TickEventWorker 是点对点方式
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        int bufferSize = 16;
        Disruptor<Tick> disruptor = new Disruptor<Tick>(
                Tick::new,
                bufferSize,
                new TickThreadFactory(),
                ProducerType.SINGLE,
                new BusySpinWaitStrategy()
        );
        disruptor.handleEventsWithWorkerPool(new TickEventWorker1(),new TickEventWorker2(),new TickEventWorker3());

        RingBuffer<Tick> ringBuffer = disruptor.start();

        //生产
        for(int i=0;i<2;i++){
            long sequence = ringBuffer.next();
            try {
                Tick event = ringBuffer.get(sequence);

            }finally {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }
        Thread.sleep(3000);
        disruptor.shutdown();
    }
}
