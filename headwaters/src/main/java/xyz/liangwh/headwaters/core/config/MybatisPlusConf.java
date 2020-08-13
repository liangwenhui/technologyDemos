package xyz.liangwh.headwaters.core.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConf {

    /**
     * 分页插件
     */
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
