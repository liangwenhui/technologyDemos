package xyz.liangwh.coredemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.coredemo.spring.aware.LwhApplicationAware;
import xyz.liangwh.coredemo.spring.bean.AppBeans;

@RestController
@RequestMapping("test")
public class TestController {

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
    public AppBeans get(String name){
        AppBeans appBeans = (AppBeans)LwhApplicationAware.getBeanByName(name);
        System.out.println(appBeans.hashCode());
        return appBeans;
    }

}
