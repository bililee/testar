package com.lee.testar.utils;

import java.lang.annotation.*;

/**
 * FirstAnnotation
 *
 * @author Lee
 * @date 2019/11/17
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FirstAnnotation {
    String value() default "";
}
