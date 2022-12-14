package com.jay.handsome.service;

import com.jay.handsome.advice.annotation.Log;
import com.jay.handsome.entity.MyEntity;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/13 16:56
 */
@Component
public class MyService {

    private MyEntity myEntity;
    @Log
    public void myFunc() {
        System.out.println("myFunc");
    }
}
