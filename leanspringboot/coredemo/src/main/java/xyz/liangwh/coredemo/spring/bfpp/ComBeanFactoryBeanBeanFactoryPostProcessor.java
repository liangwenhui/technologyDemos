package xyz.liangwh.coredemo.spring.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 将FactoryBean 通过beanFactoryPostProcessor加工一下，成为非单例模式（getBean）
 */
//@Component
public class ComBeanFactoryBeanBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("comBeanFactoryBean");
        if(beanDefinition!=null){
            System.out.println("将comBeanFactoryBean 修改为多例模式");
            beanDefinition.getPropertyValues().addPropertyValue("singleton",false);
        }

    }
}
