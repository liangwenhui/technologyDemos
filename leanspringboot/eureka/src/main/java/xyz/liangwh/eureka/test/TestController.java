package xyz.liangwh.eureka.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("test")
    public Object testEurekaClient(){
        String description = discoveryClient.description();
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-CLIENT-1");

        return null;
    }
}
