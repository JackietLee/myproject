package com.jay.handsome.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.boot.devtools.restart.classloader.RestartClassLoader;

import javax.annotation.processing.AbstractProcessor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

/**
 * 注释
 *
 * @author jay
 * @date 2022/6/21 17:29
 */
public class PersonProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里可以插入执行关键代码之前的逻辑");
        Object o1 = methodProxy.invokeSuper(o, objects);//关键代码:
        System.out.println("这里可以插入执行关键代码之后的逻辑");
        return o1;
    }

    public static void main(String[] args) throws IOException {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");

        Enhancer enhancer = new Enhancer();  // 通过CGLIB动态代理获取代理对象的过程
        enhancer.setSuperclass(Person.class);     // 设置enhancer对象的父类
        enhancer.setCallback(new PersonProxy());    // 设置enhancer的回调对象
        Person user = (Person) enhancer.create();   // 创建代理对象
        String world = user.getName();
        System.out.println(world);
        user.name = "jay";// 通过代理对象调用目标方法
        System.out.println(user.name);

//        short i = (short)(0xFFFF);
//        System.out.println(i);
//        int j = i;
//        int j2 = (int)(0xFFFF & i);
//        System.out.println(j);
//        System.out.println(j2);
//        Person person = new Person();
//        ClassLoader classLoader1 = AbstractProcessor.class.getClassLoader();
//        System.out.println(classLoader1);
//        ClassLoader classLoader = person.getClass().getClassLoader();

    }
}
