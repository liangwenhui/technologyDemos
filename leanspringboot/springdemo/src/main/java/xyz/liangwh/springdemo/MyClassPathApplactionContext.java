package xyz.liangwh.springdemo;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyClassPathApplactionContext extends ClassPathXmlApplicationContext {

public static  final String[] ABC = {"asd"};

    public MyClassPathApplactionContext(String... configLocations){
        super(configLocations);
    }

    @Override
    protected void initPropertySources() {
        System.out.println("配置必备属性（环境变量中）");
        getEnvironment().setRequiredProperties("JAVA_HOME");

    }

    /**
     *重写自定义 bean工厂方法
     * 修改beanFactory的修改
     * @param beanFactory
     */
    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        super.customizeBeanFactory(beanFactory);
        //beanFactory.setAllowBeanDefinitionOverriding(true);
    }
}
