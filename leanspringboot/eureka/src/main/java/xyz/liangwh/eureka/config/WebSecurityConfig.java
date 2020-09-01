package xyz.liangwh.eureka.config;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
    /**
     * 如果服务注册失败，以下错误是开启了防止跨域攻击，手动重写配置类方法
     * Root name 'timestamp' does not match expected ('instance') for type [simple
     * @param security
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//            security.csrf().disable();
//            super.configure(security);
//    }



}
