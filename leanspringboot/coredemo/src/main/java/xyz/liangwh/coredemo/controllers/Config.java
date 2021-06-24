package xyz.liangwh.coredemo.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Configuration
public class Config {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditer());
    }
}
