package xyz.liangwh.coredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.liangwh.coredemo.spring.bean.AppBeans;

@SpringBootApplication
public class CoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoredemoApplication.class, args);
    }


    @Bean("appAdmin")
    public AppBeans getAdmin(){
        System.out.println("实例化appAdmin");
        AppBeans b1 = new AppBeans();
        b1.setName("liangwh");
        b1.setJob("admin");

        return b1;
    }
}
