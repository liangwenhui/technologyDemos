package xyz.liangwh.coredemo.spring.proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

public class MainClass {
    public static void main(String[] args) {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(new CgLibLwhProxyCeator());
        Dog proxy = (Dog)enhancer.create();
//        proxy.say();
//        proxy.run();
        proxy.toString();
    }
}
