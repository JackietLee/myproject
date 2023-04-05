package com.jay.handsome.service;

import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/19 15:41
 */
//@Component
public class MyFactoryBeanB implements SmartFactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new MyFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MyFactoryBean.class;
    }
}
