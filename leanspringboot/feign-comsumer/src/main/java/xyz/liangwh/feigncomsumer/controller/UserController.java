package xyz.liangwh.feigncomsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.feigncomsumer.feignService.UserComsumerService;

import java.util.Collections;
import java.util.Map;

@RequestMapping("uc")
@RestController
public class UserController {
    @Autowired
    private UserComsumerService service;

    @RequestMapping("get")
    @HystrixCommand(fallbackMethod = "back")
    public String getUserName(){
      return  service.getUsername();
    }

    public  String  back(){
        return "back 1";
    }

    @GetMapping("gm1")
    public Map getMap1(){
        return service.getMap1();
    }

    @GetMapping("gm2")
    public Map getMap2(String name){
        return service.getMap2(name);
    }

    @PostMapping("pm1")
    public Map postMap1(String name){
        return service.postMap1(name);
    }


}
