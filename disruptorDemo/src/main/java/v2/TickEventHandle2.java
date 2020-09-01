package v2;


import com.lmax.disruptor.EventHandler;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TickEventHandle2 implements EventHandler<Tick> {

    @Override
    public void onEvent(Tick event, long sequence, boolean endOfBatch) throws Exception {
        TimeUnit.SECONDS.sleep(1);

        System.out.println("门票第二波操作");
        event.setName(event.getName()+"演唱会");
        event.setId(UUID.randomUUID().toString());
        event.setPrice(new BigDecimal(100));
        System.out.println(event);

    }
}
