package com.jay.handsome.advice.annotation;

import java.lang.annotation.*;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/13 17:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String value() default "";
}
