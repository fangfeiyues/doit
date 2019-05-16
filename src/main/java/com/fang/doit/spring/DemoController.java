package com.fang.doit.spring;

import com.fang.doit.spring.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by fang on 2019/4/20/020 21:05
 */
@Controller
public class DemoController {

    @Autowired
    public DemoService demoService;

    @RequestMapping("/test")
    public void test() {
        String resp = demoService.hello();
        System.out.println(resp);
    }
}
