package com.lee.testar.job.quatrzjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

/**
 * SimpleQuartzJob
 *
 * @author Lee
 * @date 2020/1/12
 */
public class SimpleQuartzJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("running");
    }
}
