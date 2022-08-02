package com.jay.handsome.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 注释
 *
 * @author jay
 * @date 2022/6/21 17:29
 */
public class Proxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里可以插入执行关键代码之前的逻辑");
//        Object o1 = methodProxy.invokeSuper(o, objects);//关键代码:
        System.out.println("这里可以插入执行关键代码之后的逻辑");
        return objects[0];
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");

        Enhancer enhancer = new Enhancer();  // 通过CGLIB动态代理获取代理对象的过程
        enhancer.setSuperclass(User.class);     // 设置enhancer对象的父类
        enhancer.setCallback(new Proxy());    // 设置enhancer的回调对象
        User user = (User) enhancer.create();   // 创建代理对象
        String world = user.say("world");  // 通过代理对象调用目标方法
        System.out.println(world);
    }
}
