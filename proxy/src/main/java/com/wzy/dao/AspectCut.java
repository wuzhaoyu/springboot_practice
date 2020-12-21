package com.wzy.dao;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/20
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Aspect
@Component
public class AspectCut {

    @Pointcut("execution(* com.wzy.dao.*.*(..) )")
    public void executionPointCut(){}

    @Pointcut("within(com.wzy.dao..*)")
    public void withinPointCut(){}

    @Pointcut("this(com.wzy.dao.DaoImpl)")
    public void thisPointCut(){}

    @Pointcut("target(com.wzy.dao.DaoImpl)")
    public void targetPointCut(){}

    @Pointcut("args(java.lang.String)")
    public void argPointCut(){}

   /* @Before("thisPointCut()")
    public void advice(){
        System.out.println("代理成功");
    }*/

    @Around("argPointCut()")
    public void advice1(ProceedingJoinPoint joinPoint){
        System.out.println("代理成功之前");
        try {
            joinPoint.proceed(new Object[]{"12"});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("代理成功之后");
    }
}
