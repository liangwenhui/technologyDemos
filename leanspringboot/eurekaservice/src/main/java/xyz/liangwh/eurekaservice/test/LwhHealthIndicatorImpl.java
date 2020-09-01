package xyz.liangwh.eurekaservice.test;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class LwhHealthIndicatorImpl implements HealthIndicator {

    private volatile boolean status = true;

    public void setStatus(boolean status){
        this.status = status;
    }

    @Override
    public Health health() {
        if(status){
            return new Health.Builder().up().build();
        }

        return new Health.Builder().down().build();
    }
}
