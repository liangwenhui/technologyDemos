package xyz.liangwh.feigncomsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignComsumerApplication.class, args);
    }

}
