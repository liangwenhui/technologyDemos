package xyz.liangwh.coredemo.spring.listener;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 监听 spring context refresh事件
 * 容器刷新时候触发
 */
@ConditionalOnExpression("'${lwh.spring.print-beans}'.equals('true')")
@Component
public class IocRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        Object source = contextRefreshedEvent.getSource();
        System.out.println("=================IocRefreshedEventListener=================");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String name:beanDefinitionNames){
            System.out.println(name);
        }
        System.out.println("================= end =================");
    }
}
