package com.lee.testar.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.dangdang.ddframe.job.util.config.ShardingItemParameters;
import com.lee.testar.job.SimpleElasticJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticJobConfig
 * 这个是elasticjob的配置
 * @author Lee
 * @date 2019/12/21
 */
@Configuration
public class ElasticJobConfig {

    private final String cron = "* */30 * * * ?";

    private final int shardingTotalCount = 2;

    private final String shardingItemParameters = "0=A,1=B";

    private final String jobParameters = "parameter";

    @Autowired
    private ZookeeperRegistryCenter registryCenter;

    @Bean
    public SimpleJob dojob(){
        return new SimpleElasticJob();
    }


    @Bean(initMethod = "init")
    public JobScheduler SimpleJobSchedule(final SimpleJob simpleJob) {
        return new SpringJobScheduler(simpleJob, registryCenter, getLiteJobConfiguration(simpleJob.getClass(),
                cron, shardingTotalCount, shardingItemParameters, jobParameters));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters,
                                                         final String jobParameters) {
        // 定义作业核心配置
//        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount).
//                shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).build();
        // 如果是分布式的话，这里的ShardingtotalCount 不应该存分片的数量，否则的话，这个单点就执行了所有的操作了
        // 知否
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, 1).
                shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, jobClass.getCanonicalName());
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
        return simpleJobRootConfig;
    }
}
