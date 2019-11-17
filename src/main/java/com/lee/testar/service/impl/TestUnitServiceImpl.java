package com.lee.testar.service.impl;

import com.lee.testar.service.TestUnitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TestUnitServiceImpl
 *
 * @author Lee
 * @date 2019/11/17
 */
@Service
public class TestUnitServiceImpl implements TestUnitService {

    private String trueValue;

    private String age;


    @Override
    public String sayNo() {
        return "no problems";
    }

    @Override
    public void sayTrueValue() {
        System.out.println(trueValue);
        System.out.println(age);
    }
}
