package com.jay.handsome.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.handsome.jay.common.Rt;
import com.jay.handsome.entity.User;
import com.jay.handsome.feign.UserInfoFeign;
import com.jay.handsome.service.MyFactoryBean;
import com.jay.handsome.service.MyService;
import com.jay.handsome.service.MyServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private MyServiceB myServiceB;

    @Autowired
    private MyServiceB myServiceB1;

    @Autowired
    private UserInfoFeign userInfoFeign;
    @GetMapping
    public void test() {
        myService.myFunc();
        try {
            myFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    @SentinelResource(value = "list", blockHandler = "exceptionHandler",fallback = "helloFallback")
    public Rt<User> getById(@PathVariable Integer id) {
        return userInfoFeign.getById(id);
    }

    public Rt<User> helloFallback(long s) {
        return Rt.fail(String.format("Halooooo %d", s));
    }

    public Rt<User> exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return Rt.fail("Oops, error occurred at " + s);
    }

}
