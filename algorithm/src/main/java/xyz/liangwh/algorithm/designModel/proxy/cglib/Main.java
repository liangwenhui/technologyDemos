package xyz.liangwh.algorithm.designModel.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import xyz.liangwh.algorithm.designModel.proxy.statoc.Taget;

public class Main {


    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Taget.class);
        enhancer.setCallback(new CglibMethodInterceptor());
        Taget taget = (Taget) enhancer.create();
        taget.sout();
    }
}
