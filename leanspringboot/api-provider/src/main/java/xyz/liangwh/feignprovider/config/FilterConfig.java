package xyz.liangwh.feignprovider.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean myBasicAuthRequestFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyBasicAuthRequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("basicAuth");
        registration.setOrder(1);
        return registration;
    }
}
