package v1;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        Thread.sleep(5000);
        System.out.println("event:"+event.getValue());
        System.out.println("sequence:"+sequence);
        System.out.println("endOfBatch:"+endOfBatch);
    }
}
