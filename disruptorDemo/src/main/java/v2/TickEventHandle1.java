package v2;


import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

public class TickEventHandle1 implements EventHandler<Tick> {

    @Override
    public void onEvent(Tick event, long sequence, boolean endOfBatch) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("门票第一波操作");
        event.setName("周杰伦");
        System.out.println(event);


    }
}
