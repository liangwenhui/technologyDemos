package v2;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class AfterMain {

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

//
//        disruptor.handleEventsWith(new TickEventHandle1())
//                .then(new TickEventHandle2())
//                .then(new TickEventHandle3());
        TickEventHandle1 t1 = new TickEventHandle1();
        TickEventHandle2 t2 = new TickEventHandle2();
        TickEventHandle3 t3 = new TickEventHandle3();
        TickEventHandle4 t4 = new TickEventHandle4();

        disruptor.handleEventsWith(t1,t2);
//        disruptor.after(t1).handleEventsWith(t2);
//        disruptor.after(t3).handleEventsWith(t4);
        disruptor.after(t1,t2).handleEventsWith(t3).then(t4);

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
