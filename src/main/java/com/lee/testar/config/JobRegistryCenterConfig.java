package com.lee.testar.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JobRegistryCenterConfig
 *
 * @author Lee
 * @date 2019/12/21
 */
@Configuration
@ConditionalOnExpression("'${zookeeper.serverlists}'.length() > 0")
public class JobRegistryCenterConfig {
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${zookeeper.serverlists}") final String serverList,
                                             @Value("${zookeeper.elasticjob.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }
}
