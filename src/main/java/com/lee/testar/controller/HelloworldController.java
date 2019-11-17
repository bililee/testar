package com.lee.testar.controller;

import com.lee.testar.servicxe.ComUtil;
import com.lee.testar.utils.FirstAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloworldController
 *
 * @author Lee
 * @date 2019/8/19
 */
@RestController
public class HelloworldController {


    @Autowired
    private ComUtil comUtil;

    @ResponseBody
    @RequestMapping("/helloworld/sayhello")
    public String hello() {
        return comUtil.write();
    }
}
