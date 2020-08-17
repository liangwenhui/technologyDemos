package xyz.liangwh.feigncomsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.feigncomsumer.feignService.UserComsumerService;

@RequestMapping("uc")
@RestController
public class UserController {
    @Autowired
    private UserComsumerService service;

    @RequestMapping("get")
    public String getUserName(){
      return  service.getUsername();
    }


}
