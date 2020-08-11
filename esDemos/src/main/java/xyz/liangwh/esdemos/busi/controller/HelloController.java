package xyz.liangwh.esdemos.busi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {

    @RequestMapping("sayHello")
    public String sayHello(String name,String msg){

        StringBuffer sb = new StringBuffer();
        sb.append("hello ").append(name).append("!");
        sb.append("\r\n");
        sb.append("I am ROoOt ").append(",I got you message :[").append(msg).append("]");
        log.info(sb.toString());
        return sb.toString();
    }
}
