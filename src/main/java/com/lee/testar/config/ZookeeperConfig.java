package com.lee.testar.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * ZookeeperConfig
 *
 * @author Lee
 * @date 2019/12/21
 */

//@Configuration
public class ZookeeperConfig {

    private ZookeeperConfiguration zookeeperConfiguration;

    public ZookeeperConfig(){
        zookeeperConfiguration.getServerLists();

    }




}
