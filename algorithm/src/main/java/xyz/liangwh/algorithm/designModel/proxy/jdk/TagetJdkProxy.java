package xyz.liangwh.algorithm.designModel.proxy.jdk;

import xyz.liangwh.algorithm.designModel.proxy.statoc.Taget;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TagetJdkProxy implements InvocationHandler {

    Object o;

    public void setO(Object o){
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk befor");
        method.invoke(o,args);
        System.out.println("jdk after");
        return null;
    }

    public Object createJTagetProxyObject(){
        return Proxy.newProxyInstance(
                ITaget.class.getClassLoader(),
                new Class[]{ITaget.class},
                this
                );
    }


}
