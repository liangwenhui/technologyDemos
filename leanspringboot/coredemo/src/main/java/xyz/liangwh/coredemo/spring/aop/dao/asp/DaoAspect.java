package xyz.liangwh.coredemo.spring.aop.dao.asp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAspect {

    @Around("execution(public * xyz.liangwh.coredemo.spring.aop.dao.ADao.selectA(String))")
    public Object  beforSelect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        args[0] = args[0].toString()+"proceed";
        return joinPoint.proceed(args);
    }

}
