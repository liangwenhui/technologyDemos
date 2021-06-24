package xyz.liangwh.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigBean {

    @Bean
    public MyClassPathApplactionContext getMyClassPathApplactionContext(){
        MyClassPathApplactionContext bean = new MyClassPathApplactionContext();
        return bean;
    }
}
