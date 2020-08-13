package xyz.liangwh.headwaters.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.liangwh.headwaters.core.HeadwatersImpl;
import xyz.liangwh.headwaters.core.model.Result;

@RestController
@RequestMapping()
public class HwIDGenController {

    @Autowired
    private HeadwatersImpl service;

    @GetMapping("/api/bucket/v1/{key}")
    public Result getId(@PathVariable("key") String key){
        Result res = service.getId(key);
        return res;
    }



}
