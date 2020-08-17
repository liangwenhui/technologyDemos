package xyz.liangwh.feignprovider.service.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.feignapi.service.UserService;
@RestController
public class UserServiceImpl implements UserService {
    @Override
    public String getUsername() {
        return "liangwh-good!!!";
    }
}
