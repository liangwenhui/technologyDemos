package v2;


import com.lmax.disruptor.EventHandler;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class TickEventHandle3 implements EventHandler<Tick> {

    @Override
    public void onEvent(Tick event, long sequence, boolean endOfBatch) throws Exception {
        TimeUnit.SECONDS.sleep(1);

        System.out.println("门票第三波操作");
        event.setName(event.getName()+"门票");
        System.out.println(event);

    }
}
