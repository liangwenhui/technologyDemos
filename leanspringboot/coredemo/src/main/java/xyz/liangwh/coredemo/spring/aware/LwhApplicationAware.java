package xyz.liangwh.coredemo.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 通过ApplicationContextAware实现，
 * 在bean实例化后，经过Aware扫描时，发现实现了ApplicationContextAware接口，
 * 就会调用setApplicationContext方法注入applicationcontext对象，
 * 这也是非常经典的一种获取上下文的方法。
 */
@Component
public class LwhApplicationAware implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LwhApplicationAware.CONTEXT = applicationContext;

    }
    public static ApplicationContext getApplicationContext() throws BeansException {
       return CONTEXT;

    }
    public static Object getBeanByName(String name){
        return CONTEXT.getBean(name);
    }

}
