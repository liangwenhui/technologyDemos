package v1;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer ;
    public LongEventProducer (RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
//    private static final EventTranslatorOneArg<LongEvent,B>


}
