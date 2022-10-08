package com.jay.handsome.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/8 14:14
 */
public class ProxyInvocationHandler implements InvocationHandler {
    private Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    //生成得到 (动态的)  代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }



    //处理代理的实例，并返回结果；
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质，就是使用反射机制实现；
        Object result = method.invoke(target, args);

        return result;
    }

    public static void main(String[] args) {
//真实角色
        UserServiceImpl userService = new UserServiceImpl();

        //代理角色，不存在的
        ProxyInvocationHandler pih = new ProxyInvocationHandler(userService);

        //动态生成代理类
        UserService proxy = (UserService) pih.getProxy();
        proxy.select();
        userService.set("jay");
        proxy.select();
        proxy.set("jay");
        proxy.select();
    }
}
