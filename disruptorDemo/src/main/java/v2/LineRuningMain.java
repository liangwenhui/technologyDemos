package v2;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import v1.LongEvent;

public class LineRuningMain {

    /**
     * EventHandler 是订阅方式
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

        //串行消费
//        disruptor.handleEventsWith(new TickEventHandle1())
//                .handleEventsWith(new TickEventHandle2())
//                .handleEventsWith(new TickEventHandle3());

        disruptor.handleEventsWith(new TickEventHandle1())
                .then(new TickEventHandle2())
                .then(new TickEventHandle3());

        //并行消费
//        disruptor.handleEventsWith(new TickEventHandle1()
//                ,new TickEventHandle2()
//                ,new TickEventHandle3());

        RingBuffer<Tick> ringBuffer = disruptor.start();

        //生产
        for(int i=0;i<1;i++){
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
