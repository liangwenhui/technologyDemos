package xyz.liangwh.coredemo.spring.proxy.jdk;

public class Cat implements Am{
    @Override
    public void say() {
        System.out.println("miao miao ~");
    }

    @Override
    public void run() {
        System.out.println("ta ta ta ~");
    }
}
