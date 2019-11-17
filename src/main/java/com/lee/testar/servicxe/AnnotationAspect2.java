package com.lee.testar.servicxe;


import com.lee.testar.utils.FirstAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AnnotationAspect
 *
 * @author Lee
 * @date 2019/11/17
 */
@Aspect
@Component
public class AnnotationAspect2 {

//    @Pointcut("@annotation(com.lee.testar.utils.FirstAnnotation)")
    @Pointcut("execution(public * com.lee.testar.servicxe.ComUtil.write(..))")
    public void annotationPointCut() {
        System.out.println("123®");
    }


    @Before("annotationPointCut()")
    public void beforePointCut() {
        System.out.println("before doing");


    }

    @After("annotationPointCut()")
    public Object haha(ProceedingJoinPoint pjp) {
        System.out.println("hahah");
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("end");
        }
        return result;
    }
}
