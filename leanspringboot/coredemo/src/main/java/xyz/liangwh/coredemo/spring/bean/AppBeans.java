package xyz.liangwh.coredemo.spring.bean;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Data
public class AppBeans {

    private String name;
    private String job;
    private String ext;
    private String ext2;




    public AppBeans(){
        System.out.println("AppBeans 构造函数");

    }
}
