package com.lee.testar;

import com.lee.testar.Application;
import com.lee.testar.service.TestUnitService;
import com.lee.testar.servicxe.ComUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TestApplication
 *
 * @author Lee
 * @date 2019/11/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    ComUtil comUtil;

    @Autowired
    TestUnitService testUnitService;


    @Test
    public void test2() {
        String words = testUnitService.sayNo();
        System.out.println(words);
    }

    @Test
    public void test() throws Exception {
        System.out.println("start");
        String word = comUtil.write();
        System.out.println(word);
    }
}
