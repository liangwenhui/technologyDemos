package xyz.liangwh.coredemo.spring.factoryBean;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import xyz.liangwh.coredemo.spring.bean.ComBean;

import java.util.UUID;

@Component
public class ComBeanFactoryBean extends AbstractFactoryBean<ComBean> {
    @Override
    public Class<?> getObjectType() {
        return ComBean.class;
    }

    @Override
    protected ComBean createInstance() throws Exception {
        ComBean comBean = new ComBean();
        comBean.setName("非常人贩");
        comBean.setExt1("exttttt1");
        //comBean.setExt2(UUID.randomUUID().toString());
        /**
         * 如果createInstance中用了
         */
        //comBean.setExt2(comBean.hashCode()+"");

        return comBean;
    }


    @Override
    public String toString() {
        return "ComBeanFactoryBean!!!";
    }
}
