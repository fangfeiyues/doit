package com.fang.doit.spring;

import org.springframework.stereotype.Service;

/**
 * created by fang on 2019/4/20/020 21:18
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String hello() {
        return "world";
    }
}
