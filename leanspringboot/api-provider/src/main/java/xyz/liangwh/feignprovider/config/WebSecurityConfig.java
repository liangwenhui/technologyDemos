package xyz.liangwh.feignprovider.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        // 表示所有的访问都必须认证，认证处理后才可以正常进行
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        // 所有的rest服务一定要设置为无状态，以提升操作效率和性能
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }




}
