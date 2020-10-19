package com.lee.testar.service.impl;

import com.lee.testar.config.myannotition.Master;
import com.lee.testar.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Master
    @Override
    public void sayTest() {
        System.out.println("sayTest");
    }
}
