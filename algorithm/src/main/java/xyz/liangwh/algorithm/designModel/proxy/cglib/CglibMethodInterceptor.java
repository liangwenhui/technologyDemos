package xyz.liangwh.algorithm.designModel.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMethodInterceptor  implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println(o.getClass().getSuperclass().getName());
        System.out.println("cglib before");
        Object res = null;
        //res = methodProxy.invokeSuper(o,objects);
        res = methodProxy.invokeSuper(o,objects);
        System.out.println("cglib after");

        //methodProxy.invokeSuper(o,objects);
        return res;
    }
}
