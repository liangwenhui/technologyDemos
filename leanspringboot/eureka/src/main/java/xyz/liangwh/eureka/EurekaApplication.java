package xyz.liangwh.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }


//    @Configuration(proxyBeanMethods = false)
//    @ConditionalOnMissingClass("org.springframework.retry.support.RetryTemplate")
//    static class LoadBalancerInterceptorConfig {
//
//
//
//        @Bean
//        public RestTemplateCustomizer restTemplateCustomizer(
//                final LoadBalancerInterceptor loadBalancerInterceptor) {
//            return restTemplate -> {
//                List list = new ArrayList<>(
//                        restTemplate.getInterceptors());
//                list.add(loadBalancerInterceptor);
//                restTemplate.setInterceptors(list);
//            };
//        }
//
//    }

}
