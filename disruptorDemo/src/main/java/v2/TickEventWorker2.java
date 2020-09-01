package v2;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

public class TickEventWorker2 implements WorkHandler<Tick> {

    @Override
    public void onEvent(Tick event) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("门票第2波操作");
        event.setName("周杰伦2");
        System.out.println(event);
    }
}
