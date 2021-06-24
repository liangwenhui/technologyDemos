package xyz.liangwh.coredemo.spring.proxy.jdk;

public class MainClass {


    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Cat cat = new Cat();
        JDKLwhProxyCeator ceator = new JDKLwhProxyCeator(cat);
        Am proxy = ceator.getProxy();
//        proxy.say();
//        proxy.run();
        proxy.toString();
    }
}
