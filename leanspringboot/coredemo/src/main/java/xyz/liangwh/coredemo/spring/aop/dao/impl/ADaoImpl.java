package xyz.liangwh.coredemo.spring.aop.dao.impl;

import org.springframework.stereotype.Component;
import xyz.liangwh.coredemo.spring.aop.dao.ADao;
@Component
public class ADaoImpl implements ADao {

    @Override
    public String selectA(String a) {
        System.out.println(a);
        return a;
    }
}
