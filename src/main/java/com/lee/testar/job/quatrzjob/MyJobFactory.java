package com.lee.testar.job.quatrzjob;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * MyJobFactory
 *
 * @author Lee
 * @date 2020/1/12
 */
@Component
public class MyJobFactory extends AdaptableJobFactory {


    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        return jobInstance;
    }
}
