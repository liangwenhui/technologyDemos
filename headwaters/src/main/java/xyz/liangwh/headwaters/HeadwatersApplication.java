package xyz.liangwh.headwaters;

import lombok.extern.slf4j.Slf4j;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("xyz.liangwh.headwaters.core.dao")
@SpringBootApplication
@Slf4j
public class HeadwatersApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeadwatersApplication.class, args);
        log.info("############################启动成功#########################");
    }

}
