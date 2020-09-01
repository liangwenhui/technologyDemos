package v2;


import com.lmax.disruptor.EventHandler;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class TickEventHandle4 implements EventHandler<Tick> {

    @Override
    public void onEvent(Tick event, long sequence, boolean endOfBatch) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("门票加价操作");
        event.setPrice(event.getPrice().add(new BigDecimal(1000)));
        System.out.println(event);


    }
}
