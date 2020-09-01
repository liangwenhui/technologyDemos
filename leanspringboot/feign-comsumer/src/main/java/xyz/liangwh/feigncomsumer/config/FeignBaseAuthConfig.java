package xyz.liangwh.feigncomsumer.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration

/**
 * 如果在配置类上添加了@Configuration注解，并且该类在@ComponentScan所扫描的包中，那么该类中的配置信息就会被所有的@FeignClient共享。最佳实践是：不指定@Configuration注解（或者指定configuration，用注解忽略），而是手动：
 *
 * @FeignClient(name = "service-valuation",configuration = FeignAuthConfiguration.class)
 */
public class FeignBaseAuthConfig {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor("lwh","lwh1618");
        return basicAuthRequestInterceptor;
    }

}
