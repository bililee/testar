package com.lee.testar.job.quatrzjob;

import org.quartz.SchedulerException;

/**
 * ServiceException
 *
 * @author Lee
 * @date 2020/1/12
 */
public class ServiceException extends Exception {


    public ServiceException(String message)  throws SchedulerException{
        super(message);
//        throw new SchedulerException(message);
    }
}
