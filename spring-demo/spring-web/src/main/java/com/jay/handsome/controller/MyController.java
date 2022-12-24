package com.jay.handsome.controller;

import com.jay.handsome.service.MyFactoryBean;
import com.jay.handsome.service.MyService;
import com.jay.handsome.service.MyServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/13 16:57
 */
@RestController
@RequestMapping("/myController")
public class MyController {

    @Autowired
    private MyService myService;

    @Autowired
    private MyFactoryBean myFactoryBean;
    @GetMapping
    public void test() {
        myService.myFunc();
        try {
            myFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void test2() {

    }
}
