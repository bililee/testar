package com.lee.testar;

import com.lee.testar.service.TestUnitService;
import com.lee.testar.service.impl.TestUnitServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test2Application
 *
 * @author Lee
 * @date 2019/11/17
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        TestUnitServiceImpl.class,
        PropertySourcesPlaceholderConfigurer.class
})
@TestPropertySource(properties = {"config.username=myname", "config.age=18"})
public class Test2Application {

    @Autowired
    TestUnitService testUnitService;

    @Test
    public void test() {
        String words = testUnitService.sayNo();
        System.out.println(words);
    }

    @Test
    public void test2() {
        testUnitService.sayTrueValue();

    }
}
