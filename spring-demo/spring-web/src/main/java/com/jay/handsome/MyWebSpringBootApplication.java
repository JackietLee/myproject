package com.jay.handsome;

import com.jay.handsome.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyWebSpringBootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyWebSpringBootApplication.class, args);
        MyService bean = context.getBean(MyService.class);
        System.out.println(bean);
    }
}