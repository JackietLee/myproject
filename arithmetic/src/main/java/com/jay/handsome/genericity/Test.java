package com.jay.handsome.genericity;


import org.springframework.core.ResolvableType;

import java.lang.reflect.*;

/**
 * 如何获取泛型具体类型   Gson的TypeToken
 *
 * @author jay
 * @date 2022/10/20 17:23
 */
public class Test<T extends Base<M>, M> {
    public T base;
    public T[] base1;

//    public void setBase(T base) {
//        this.base = base;
//        Class<? extends Base> aClass = base.getClass();
//        System.out.println(aClass);
//    }
//
//    public void print() {
//        Class<? extends Base> aClass = base.getClass();
//        System.out.println(aClass);
//    }

    public void printType() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        System.out.println(entityClass);
    }

    public static class SubTest extends Test<SubBase, String> {

    }
    public static void main(String[] args) {
        SubTest subBaseTest = new SubTest();
        Type genericSuperclass = subBaseTest.getClass().getGenericSuperclass();
        TypeVariable<? extends Class<?>>[] typeParameters = subBaseTest.getClass().getSuperclass().getTypeParameters();
        if (genericSuperclass instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                System.out.println(actualTypeArguments[i]);
                System.out.println(typeParameters[i]);
            }

        }
        try {
            ResolvableType t = ResolvableType.forField(subBaseTest.getClass().getField("base"));
            ResolvableType superType = t.getSuperType();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
//        subBaseTest.print();
        try {
            Field base2 = subBaseTest.getClass().getField("base");
            Type genericType = base2.getType();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        Field[] fields = subBaseTest.getClass().getFields();
        for (Field field : fields) {
//            Type genericType = field.getGenericType();
//            System.out.println("Is it genericArrayType:" + (genericType instanceof GenericArrayType));
//
//            if (genericType instanceof GenericArrayType) {
//                GenericArrayType genericArrayType = (GenericArrayType) genericType;
//                System.out.println(genericArrayType.getTypeName());
//                Type genericComponentType = genericArrayType.getGenericComponentType();
//                System.out.println(genericComponentType);
//            }
            Type genericSuperclass1 = field.getType().getGenericSuperclass();
            if (genericSuperclass1 instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
            System.out.println();
        }
    }
}
