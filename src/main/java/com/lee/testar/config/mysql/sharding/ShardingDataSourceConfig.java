package com.lee.testar.config.mysql.sharding;


import com.google.common.collect.Maps;
import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingDataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(ShardingDataSourceConfig.class);

    @Autowired(required = false)
    private ShardingMasterSlaveConfig shardingMasterSlaveConfig;

    @ConfigurationProperties("sharding.jdbc.data-sources.ds_master")
    @Bean
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties("sharding.jdbc.data-sources.ds_slave_0")
    @Bean
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "shardingDataSource")
    public DataSource masterSlaveDataSource(@Qualifier("masterDataSource") DataSource masterDatasource, @Qualifier("slaveDataSource") DataSource slaveDataSource) throws SQLException {
        System.out.println(23);
//        shardingMasterSlaveConfig.getDataSources().forEach((k, v) -> configDataSource(v));
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("ds_master", masterDatasource);
        dataSourceMap.put("ds_slave_0", slaveDataSource);
//        dataSourceMap.putAll(shardingMasterSlaveConfig.getDataSources());
        // 最重要的还是这一步，将datasources 通过sharding-jdbc的一个创建工厂进行处理
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, shardingMasterSlaveConfig.getMasterSlaveRule(),  new HashMap<>(), new Properties());
        logger.info("masterSlaveDataSource config complete");
        return dataSource;
    }

}
