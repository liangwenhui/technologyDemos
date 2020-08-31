package v1;

import com.lmax.disruptor.WorkHandler;

public class LongEventWorkHandle implements WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event) throws Exception {

        System.out.println(Thread.currentThread().getName());

    }
}
