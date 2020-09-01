package xyz.liangwh.coredemo.spring.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SoutBeanNames implements BeanNameAware {

    public SoutBeanNames(){
        System.out.println("AppBeans 构造 函数");

    }

    @Override
    public void setBeanName(String s) {
        System.out.println(s);
    }

    @PostConstruct
    public void init(){
        System.out.println("AppBeans init 函数");

    }
}
