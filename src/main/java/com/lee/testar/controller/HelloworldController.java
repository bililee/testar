package com.lee.testar.controller;

import com.lee.testar.service.ZookeeperService;
import com.lee.testar.servicxe.ComUtil;
import com.lee.testar.utils.FirstAnnotation;
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
    private ComUtil comUtil;

    @ResponseBody
    @RequestMapping("/helloworld/sayhello")
    public String hello() {
        return comUtil.write();
    }

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
}
