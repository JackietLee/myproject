package com.jay.handsome.processor;

import com.jay.handsome.entity.MyEntity;
import com.jay.handsome.service.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/13 18:03
 */
@Component
public class MyPostProcessor implements BeanPostProcessor, CommandLineRunner {
    private Object myService;

    @Override
    public void run(String... args) throws Exception {
        Field myEntity = myService.getClass().getDeclaredField("myEntity");
        myEntity.setAccessible(true);
        myEntity.set(myService, new MyEntity());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == MyService.class) {
            myService = bean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
