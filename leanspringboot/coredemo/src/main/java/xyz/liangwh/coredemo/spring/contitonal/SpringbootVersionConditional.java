package xyz.liangwh.coredemo.spring.contitonal;


import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 实现 Condition matches方法
 * 通过 @Conditional(SpringbootVersionConditional.class)
 * 决定是否实例化bean
 */
public class SpringbootVersionConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String version = SpringBootVersion.getVersion();

        return version.startsWith("1");
    }
}
