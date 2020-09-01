package xyz.liangwh.coredemo.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor bean实例化后调用
 */
@Component
public class LwhBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("soutBeanNames"))
            System.out.println("LwhBeanPostProcessor postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("soutBeanNames"))
            System.out.println("LwhBeanPostProcessor postProcessAfterInitialization");
        return bean;
    }
}
