package xyz.liangwh.coredemo.spring.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import xyz.liangwh.coredemo.spring.bean.AppBeans;

import java.util.Map;
import java.util.Set;

/**
 * 动态注册bean
 * BeanFactoryPostProcessor
 * bean工厂的bean属性处理容器，说通俗一些就是可以管理我们的bean工厂内所有的beandefinition（未实例化）数据，可以随心所欲的修改属性。
 */
@Component
public class LwhBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("LwhBeanFactoryPostProcessor postProcessBeanDefinitionRegistry handle bean");

        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName("xyz.liangwh.coredemo.spring.bean.AppBeans");
        beanDefinition.setAttribute("ext","${job.name}");
        beanDefinitionRegistry.registerBeanDefinition("newJob",beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("LwhBeanFactoryPostProcessor postProcessBeanFactory handle bean");
        //((DefaultListableBeanFactory) configurableListableBeanFactory).registerBeanDefinition();
        BeanDefinition newJob = configurableListableBeanFactory.getBeanDefinition("newJob");

        if(newJob!=null){
            MutablePropertyValues propertyValues = newJob.getPropertyValues();
            Object o = newJob.getAttribute("ext");
            propertyValues.add("ext",o);
            propertyValues.addPropertyValue("name","hjn");
            propertyValues.addPropertyValue("job","wife");
        }

//        Map<String, AppBeans> beansOfType = configurableListableBeanFactory.getBeansOfType(AppBeans.class);
//        Set<String> strings = beansOfType.keySet();
//        for(String beanNames:strings){
//            AppBeans appBeans = beansOfType.get(beanNames);
//            appBeans.setExt2("add by bean post process");
//        }
    }
}
