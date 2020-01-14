package com.lee.testar.job.quatrzjob;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandler
 *
 * @author Lee
 * @date 2020/1/12
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    public String handler(ServiceException ex) {
        return "hello";

    }
}
