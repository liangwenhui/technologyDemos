package xyz.liangwh.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import xyz.liangwh.eurekaservice.test.MyClientHttpRequestInterceptor;

import java.util.Arrays;

/**
 * 1. 主程序入口添加@EnableFeignClients注解开启对Feign Client扫描加载处理。
 * 根据Feign Client的开发规范，定义接口并加@FeignClient注解。
 *
 * 2. 当程序启动时，会进行包扫描，扫描所有@FeignClient注解的类，并将这些信息注入Spring IoC容器中。
 * 当定义的Feign接口中的方法被调用时，通过JDK的代理方式，来生成具体的RequestTemplate。
 * 当生成代理时，Feign会为每个接口方法创建一个RequestTemplate对象，
 * 该对象封装了HTTP请求需要的全部信息，如请求参数名、请求方法等信息都在这个过程中确定。
 *
 * 3. 然后由RequestTemplate生成Request，然后把这个Request交给client处理，
 * 这里指的Client可以是JDK原生的URLConnection、Apache的Http Client，也可以是Okhttp。
 * 最后Client被封装到LoadBalanceClient类，这个类结合Ribbon负载均衡发起服务之间的调用。
 */
@SpringBootApplication
@EnableEurekaClient
@Configuration
public class EurekaserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserviceApplication.class, args);
    }



    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new MyClientHttpRequestInterceptor());

        return template;

    }
}
