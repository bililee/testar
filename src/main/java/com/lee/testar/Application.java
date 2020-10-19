package com.lee.testar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Application
 *
 * @author Lee
 * @date 2019/8/19
 */
@SpringBootApplication
@MapperScan("com.lee.testar.mapper")
@ComponentScan("com.lee.testar.*")
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
