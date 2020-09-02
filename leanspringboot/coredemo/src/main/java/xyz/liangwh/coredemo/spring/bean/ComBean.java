package xyz.liangwh.coredemo.spring.bean;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class ComBean {
    private AppBeans b;
    private String name;
    private String ext1;
    private String ext2;
}
