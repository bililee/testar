package com.lee.testar.config.mysql;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
//@Aspect
//@Component
public class DataSourceAop {


    @Pointcut("@annotation(com.lee.testar.config.myannotition.Slave)")
    public void readPointCut(){
        System.out.println("readpoint");
    }

    @Pointcut("@annotation(com.lee.testar.config.myannotition.Master)")
    public void writePointCut(){
        System.out.println("writepoint");

    }

    @Around("readPointCut()")
    public void read() {
        DBContextHolder.slave();
        System.out.println("read");

    }

    @Around("writePointCut()")
    public void write() {
        DBContextHolder.master();
        System.out.println("write");

    }
}
