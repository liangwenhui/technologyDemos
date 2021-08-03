package xyz.liangwh.algorithm.reflex;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyMain implements InvocationHandler {

    ProxyTest obj ;

    public JdkProxyMain (ProxyTest target){
        obj = target;
    }

    public ProxyTest getProxyObj(){
        return (ProxyTest)Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.getName().equals("say")){
            System.out.println("befor");
            Object res = method.invoke(obj,args);
            System.out.println("after");
            return res;
        }

        return method.invoke(obj,args);
    }


    public static void main(String[] args) {
        JdkProxyMain jdkProxyMain = new JdkProxyMain(new ProxyTestImpl());
        ProxyTest proxyObj = jdkProxyMain.getProxyObj();
        proxyObj.say("nihao");
        System.out.println(proxyObj.get("helo"));
    }

}
