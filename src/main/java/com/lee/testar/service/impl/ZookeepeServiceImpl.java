package com.lee.testar.service.impl;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.lee.testar.config.JobRegistryCenterConfig;
import com.lee.testar.service.BaseZookeeper;
import com.lee.testar.service.ZookeeperService;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ZookeepeServiceImpl
 * zookeeper 的具体服务实现类
 * @author Lee
 * @date 2019/12/21
 */
@Service
public class ZookeepeServiceImpl implements ZookeeperService {

    Logger logger = LoggerFactory.getLogger(ZookeeperService.class);

    @Resource
    JobRegistryCenterConfig jobRegistryCenterConfig;

    @Value("${zookeeper.serverlists}")
    private String host;

    @Override
    public Object getAllPath() {
        BaseZookeeper baseZookeeper = new BaseZookeeper("47.111.166.134:2181,47.111.166.134:2182,47.111.166.134:2183");
        try {
            baseZookeeper.conncetZookeeper("47.111.166.134:2181,47.111.166.134:2182,47.111.166.134:2183");
            List<String> children = baseZookeeper.getChildren("/");
            Iterator list = children.iterator();
            while (list.hasNext()) {
                String temp = (String)list.next();
                System.out.println("temp:" + temp);
            }
            baseZookeeper.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                baseZookeeper.closeConnection();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Object getOnePath(String namespace) {
        BaseZookeeper baseZookeeper = new BaseZookeeper(host);
        List<String> children = new ArrayList<>();
        try {
            children = baseZookeeper.getChildren(namespace);
        } catch (Exception exp) {
            logger.error(exp.getMessage(), exp);
        }
        children.forEach(System.out::println);
        return children;
    }
}
