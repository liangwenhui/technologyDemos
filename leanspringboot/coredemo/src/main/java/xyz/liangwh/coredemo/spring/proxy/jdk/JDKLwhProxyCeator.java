package xyz.liangwh.coredemo.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKLwhProxyCeator implements InvocationHandler {

    Object target ;

    public JDKLwhProxyCeator(Object target){
        this.target = target;
    }

    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("befor invoke");
        Object o =  method.invoke(target,args);
        System.out.println("after invoke");

        return o;
    }
}
