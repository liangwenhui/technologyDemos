package xyz.liangwh.feigncomsumer.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.liangwh.feignapi.service.UserService;
import xyz.liangwh.feigncomsumer.config.FeignBaseAuthConfig;

import java.util.Collections;
import java.util.Map;

@FeignClient(name="provider",configuration = FeignBaseAuthConfig.class)
public interface UserComsumerService extends UserService {

    /**
     * 这里的注解，是给feign看的，feign根据注解生成请求
     * @return
     */

    @GetMapping("getMap1")
    public Map getMap1();

    @GetMapping("getMap2")
    public Map getMap2(@RequestParam("name") String name);

    @PostMapping("postMap1")
    public Map postMap1(@RequestParam("name") String name);
}
