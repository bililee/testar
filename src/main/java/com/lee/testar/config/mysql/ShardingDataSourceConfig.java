package com.lee.testar.config.mysql;


import com.google.common.collect.Maps;
import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "sharding-jdbc")
public class ShardingDataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(ShardingDataSourceConfig.class);

    @Autowired(required = false)
    private ShardingMasterSlaveConfig shardingMasterSlaveConfig;



//    @ConfigurationProperties("spring.datasource.slave")
////    @Bean
////    public DataSource slaveDataSource(){
////        DataSource dataSource =  DataSourceBuilder.create().build();
////        return dataSource;
////    }
////
////    @ConfigurationProperties("spring.datasource.master")
////    @Bean(name = "masterDataSource")
////    public DataSource masterDataSource(){
////        DataSource dataSource =  DataSourceBuilder.create().build();
////        return dataSource;
////    }


    @ConfigurationProperties("sharding-jdbc.data-sources.ds-master")
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(){
        DataSource dataSource =  DataSourceBuilder.create().build();
        return dataSource;
    }

    @ConfigurationProperties("sharding-jdbc.data-sources.ds-slave-0")
    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource(){
        DataSource dataSource =  DataSourceBuilder.create().build();
        return dataSource;
    }


    @Bean(name = "shardingDataSource")
    public DataSource masterSlaveDataSource(@Qualifier("masterDataSource") DataSource masterDatasource, @Qualifier("slaveDataSource") DataSource slaveDataSource) throws SQLException {
        System.out.println(23);
//        shardingMasterSlaveConfig.getDataSources().forEach((k, v) -> configDataSource(v));
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("ds-master", masterDatasource);
        dataSourceMap.put("ds-slave-0", slaveDataSource);
//        dataSourceMap.putAll(shardingMasterSlaveConfig.getDataSources());
        // 最重要的还是这一步，将datasources 通过sharding-jdbc的一个创建工厂进行处理
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, shardingMasterSlaveConfig.getMasterSlaveRule(),  new HashMap<>(), new Properties());
        logger.info("masterSlaveDataSource config complete");
        return dataSource;
    }

}
