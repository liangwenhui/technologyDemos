package xyz.liangwh.coredemo.spring.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import xyz.liangwh.coredemo.spring.bean.AppBeans;

import java.util.Map;
import java.util.Set;

/**
 * 动态修改bean
 */
@Component
public class AppBeansBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
//        BeanDefinition appAdmin = factory.getBeanDefinition("newJob");
//        if (appAdmin!=null){
//            appAdmin.getPropertyValues().addPropertyValue("ext2","gogogogo");
//        }
//        AppBeans newJob = (AppBeans) factory.getBean("newJob");
//        newJob.setExt2("ggggg");
        System.out.println("AppBeansBeanFactoryPostProcessor handle beans");
        Map<String, AppBeans> beansOfType = factory.getBeansOfType(AppBeans.class);
        Set<String> strings = beansOfType.keySet();
        for(String beanNames:strings){
            AppBeans appBeans = beansOfType.get(beanNames);
            appBeans.setExt2("add by AppBeansBeanFactoryPostProcessor");
        }

    }
}
