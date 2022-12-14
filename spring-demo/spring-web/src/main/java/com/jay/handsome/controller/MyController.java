package com.jay.handsome.controller;

import com.jay.handsome.service.MyService;
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

    @GetMapping
    public void test() {
        myService.myFunc();
    }
}
