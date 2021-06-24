package xyz.liangwh.coredemo.spring.dynamicRegist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class BeanA {

@Autowired
BeanB b;

    public void sayA(){
        System.out.println("A@@@@@");
    }
}
