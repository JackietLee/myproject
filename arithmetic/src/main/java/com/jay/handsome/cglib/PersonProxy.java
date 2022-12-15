package com.jay.handsome.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 注释
 *
 * @author jay
 * @date 2022/6/21 17:29
 */
public class PersonProxy implements MethodInterceptor {

    private final Object target;

    public PersonProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里可以插入执行关键代码之前的逻辑");
        Object o1 = methodProxy.invokeSuper(o, args);//只需要代理对象，如果所有指定类的对象都需要被代理，可以使用invokeSuper  this指代代理对象
//        Object o1 = methodProxy.invoke(target, args);//需要被代理的对象，如果被代理类的对象一部分需要代理，一部分不需要被代理  跟spring一样，this指代被代理的对象
        System.out.println("这里可以插入执行关键代码之后的逻辑");
        return o1;
    }

    public Object getProxyInstance() {
        //1. 创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2. 设置父类
        enhancer.setSuperclass(target.getClass());
        //3. 设置回调函数
        enhancer.setCallback(this);
        //4. 创建子类对象，即代理对象
        return enhancer.create();
    }

    public static void main(String[] args) {
//        try {
//            test1();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }

        test2();
    }

    public static void test1()  throws IOException, NoSuchFieldException{
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        Person person = new Person();
        PersonProxy personProxy = new PersonProxy(person);
        Person user = (Person) personProxy.getProxyInstance();   // 创建代理对象
        String world = user.getName();
        System.out.println(world);
        Field name = Person.class.getDeclaredField("name");
        person.name = "jay";
        ReflectionUtils.setField(name, person, "jay");
//        person.setName("jay");
        System.out.println(user.getName());
        user.setName("jay1");
        System.out.println(user.getName());

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
        String jay = URLEncoder.encode("jay", StandardCharsets.UTF_8);
        String decode = URLDecoder.decode(jay, StandardCharsets.UTF_8);
        System.out.println(decode);
    }

    public static void test2(){
        Person person = new Person();
        PersonProxy personProxy = new PersonProxy(person);
        Person user = (Person) personProxy.getProxyInstance();   // 创建代理对象
        user.one();
    }
}
