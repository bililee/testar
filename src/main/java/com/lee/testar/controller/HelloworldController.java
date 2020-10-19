package com.lee.testar.controller;

import com.lee.testar.config.myannotition.Master;
import com.lee.testar.config.myannotition.Slave;
import com.lee.testar.config.mysql.DBContextHolder;
import com.lee.testar.mapper.TidMapper;
import com.lee.testar.service.TestService;
import com.lee.testar.service.ZookeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * HelloworldController
 *
 * @author Lee
 * @date 2019/8/19
 */
@RestController
public class HelloworldController {


    @Resource
    private ZookeeperService zookeeperService;

    @Autowired
    private TestService testService;

    /**
     * 获取zookeeper的后台管理页面
     * @return
     */
    @RequestMapping("hello/getpath")
    @ResponseBody
    public Object getZookeeper() {
        return zookeeperService.getAllPath();
    }

    @RequestMapping("hello/getone")
    @ResponseBody
    public Object getZookeeper(
            @RequestParam(name = "namespace", defaultValue = "/") String namespace
    ) {
        return zookeeperService.getOnePath(namespace);
    }


    @Autowired
    private TidMapper tidMapper;

    @RequestMapping("hello/get")
    @ResponseBody
    @Slave
    public Object getZookeeper1(
            @RequestParam(name = "id", defaultValue = "1")  Integer id
    ) {
        return tidMapper.selectByid(id);
    }

    @RequestMapping("hello/get2")
    @ResponseBody
    public Object getZookeeper2(
            @RequestParam(name = "id", defaultValue = "1")  Integer id
    ) {
        Object res1 = DBContextHolder.get();
        testService.sayTest();
        Object res = DBContextHolder.get();
        return tidMapper.selectByid(id);
    }


}
