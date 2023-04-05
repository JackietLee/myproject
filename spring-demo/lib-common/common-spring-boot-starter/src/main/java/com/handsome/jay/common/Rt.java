/*
 * Copyright (c) 2020-2030 江苏丰尚
 * 不能修改和删除上面的版权声明
 * 此代码属于江苏丰尚深圳研究院部门编写，在未经允许的情况下不得传播复制
 */
package com.handsome.jay.common;

import com.google.gson.Gson;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一接口返回对象
 *
 * @author Leoly Gu
 * @date 2020-10-23 12:02:13
 */
@Data
public class Rt<T> implements Serializable {
    private static final long serialVersionUID = 828655026342216609L;
    private Integer code = 0;
    private String desc = "";
    private T data;

    private Rt() {
    }

    public Rt(RtEnum rtEnum) {
        this.code = rtEnum.getCode();
        this.desc = rtEnum.getDesc();
    }

    public static <T> Rt<T> ok() {
        Rt<T> rt = new Rt<>(RtEnum.SUCCESS);
        return rt;
    }

    public static <T> Rt<T> ok(T result) {
        Rt<T> rt = Rt.ok();
        rt.setData(result);
        return rt;
    }

    public static <T> Rt<T> error() {
        return new Rt<>(RtEnum.ERROR);
    }

    public static <T> Rt<T> fail(String message) {
        Rt<T> rt = new Rt<>(RtEnum.FAILURE);
        rt.setDesc(message);
        return rt;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
