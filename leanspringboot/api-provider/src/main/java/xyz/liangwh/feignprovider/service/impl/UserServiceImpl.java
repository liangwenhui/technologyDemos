package xyz.liangwh.feignprovider.service.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.feignapi.service.UserService;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserServiceImpl implements UserService {
    @Override
    public String getUsername() {
        return "liangwh-good!!!";
    }


    @GetMapping("getMap1")
    public Map getMap1(){
        return Collections.singletonMap("好","起来了");
    }

    @GetMapping("getMap2")
    public Map getMap2(String name){
        return Collections.singletonMap(name,"好起来了");
    }

    @PostMapping("postMap1")
    public Map postMap1(String name){
        return Collections.singletonMap(name,"好起来了");
    }

}
