package xyz.liangwh.coredemo.spring.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;

public class CgLibLwhProxyCeator implements MethodInterceptor {




    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("befor");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("after");

        return invoke;
    }

}
