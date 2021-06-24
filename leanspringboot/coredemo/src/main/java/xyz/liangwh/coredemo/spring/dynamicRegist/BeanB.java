package xyz.liangwh.coredemo.spring.dynamicRegist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BeanB {


    @Autowired
    BeanA a;


    public String dos(){
        a.sayA();
        return "ok";
    }

}
