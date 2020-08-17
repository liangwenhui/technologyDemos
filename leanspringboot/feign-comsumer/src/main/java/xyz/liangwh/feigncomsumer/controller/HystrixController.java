package xyz.liangwh.feigncomsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.feignapi.service.UserService;
import xyz.liangwh.feigncomsumer.feignService.UserComsumerService;

@RestController
@RequestMapping("hy")
public class HystrixController {

    @Autowired
    private UserComsumerService service;

    @RequestMapping("get")
    public String getUserName(){
        return  service.getUsername();
    }

}
