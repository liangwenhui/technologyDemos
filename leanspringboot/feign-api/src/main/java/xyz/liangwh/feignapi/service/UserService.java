package xyz.liangwh.feignapi.service;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
public interface UserService {
    @RequestMapping("getName")
    public String getUsername();
}
