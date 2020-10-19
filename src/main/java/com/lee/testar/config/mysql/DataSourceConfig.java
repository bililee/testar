package com.lee.testar.config.mysql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//public class DataSourceConfig {
//
//    @ConfigurationProperties("spring.datasource.master")
//    @Bean
//    public DataSource masterDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @ConfigurationProperties("spring.datasource.slave")
//    @Bean
//    public DataSource slaveDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource){
//        Map<Object,Object> targetDataSource = new HashMap<>();
//        targetDataSource.put(DbEnum.MASTER, masterDataSource);
//        targetDataSource.put(DbEnum.SLAVE, slaveDataSource);
//        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
//        myRoutingDataSource.setTargetDataSources(targetDataSource);
//        myRoutingDataSource.setDefaultTargetDataSource(targetDataSource.get(DbEnum.MASTER));
//        return myRoutingDataSource;
//    }
//

//}
