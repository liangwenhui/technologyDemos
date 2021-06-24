package xyz.liangwh.coredemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import xyz.liangwh.coredemo.spring.LWHConfiguration2;
import xyz.liangwh.coredemo.spring.aop.dao.ADao;
import xyz.liangwh.coredemo.spring.aop.dao.impl.ADaoImpl;
import xyz.liangwh.coredemo.spring.aware.LwhApplicationAware;
import xyz.liangwh.coredemo.spring.bean.AppBeans;
import xyz.liangwh.coredemo.spring.bean.ComBean;
import xyz.liangwh.coredemo.spring.dynamicRegist.BeanB;
import xyz.liangwh.out.LWHConfiguration3;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private ADao dao;

    @Autowired
    BeanB b;



    @Autowired
    LWHConfiguration3 lwhConfiguration3;

    @RequestMapping("testB")
    public String testB(){
        return b.dos();
    }

    @RequestMapping("testAop")
    public String testAop(){
//        String a = dao.selectA("nb");
//        return a;
        return lwhConfiguration3.toString();
    }

    @RequestMapping("testBool")
    public boolean testBool(@RequestBody  User user){
//        String a = dao.selectA("nb");
//        return a;
        return user.isAa();
    }

    @RequestMapping("getAdmin")
    public AppBeans getAdmin(){
        AppBeans appBeans = (AppBeans)LwhApplicationAware.getBeanByName("appAdmin");
        System.out.println(appBeans.hashCode());
        return appBeans;
    }

    @RequestMapping("setAdmin")
    public AppBeans setAdmin(){
        AppBeans appBeans = (AppBeans)LwhApplicationAware.getBeanByName("appAdmin");
        appBeans.setName("hjn");
        System.out.println(appBeans.hashCode());
        return appBeans;
    }

    @RequestMapping("get")
    public Object get(String name){
        Object o = LwhApplicationAware.getBeanByName(name);
        System.out.println(o.hashCode());
        return o;
    }

    @RequestMapping("gset")
    public Object gset(String name){
        ComBean o = (ComBean)LwhApplicationAware.getBeanByName(name);
        System.out.println(o.hashCode());
        o.setName(UUID.randomUUID().toString());
        return o;
    }

    /**
     * 通过获取handlemapping 获取应用内所有url信息
     * @return
     */
    @RequestMapping("index")
    public Object index(){
        String[] beanNamesForType = LwhApplicationAware.getApplicationContext().getBeanNamesForType(HandlerMapping.class);
        for(String name:beanNamesForType){
            Object o = LwhApplicationAware.getApplicationContext().getBean(name);
            if(o instanceof RequestMappingHandlerMapping){
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = ((RequestMappingHandlerMapping) o).getHandlerMethods();
                handlerMethods.forEach((mappingInfo,method)->{
                    mappingInfo.getPatternsCondition().getPatterns().forEach(url->{
                        System.out.println(method.getBeanType().getName()+"#"
                                +method.getMethod().getName()
                                +" url: "+url);
                    });
                });

            }else if(o instanceof BeanNameUrlHandlerMapping){
                Map<String, Object> handlerMap = ((BeanNameUrlHandlerMapping) o).getHandlerMap();
                handlerMap.forEach((mapping,obj)->{
                    System.out.println(obj+" urls: "+mapping);
                });

            }
        }

        return null;
    }


}
