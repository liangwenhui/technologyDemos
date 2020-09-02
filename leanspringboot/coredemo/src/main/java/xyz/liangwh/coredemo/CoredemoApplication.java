package xyz.liangwh.coredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import xyz.liangwh.coredemo.spring.bean.AppBeans;
import xyz.liangwh.coredemo.spring.contitonal.SpringbootVersionConditional;

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
    @Bean("springAdmin")
    @Conditional(SpringbootVersionConditional.class)
    public AppBeans getSpringAdmin(){
        System.out.println("springAdmin");
        AppBeans b1 = new AppBeans();
        b1.setName("SpringAdmin");
        b1.setJob("springAdmin");
        return b1;
    }

}
