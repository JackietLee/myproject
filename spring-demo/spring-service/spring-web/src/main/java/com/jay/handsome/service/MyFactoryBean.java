package com.jay.handsome.service;

import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/19 15:41
 */
@Component
public class MyFactoryBean implements SmartFactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new MyServiceB();
    }

    @Override
    public Class<?> getObjectType() {
        return MyServiceB.class;
    }

//    @Override
//    public boolean isPrototype() {
//        return true;
//    }

    /**
     * 以这个为准
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
