/*
 * Copyright (c) 2020-2030 江苏丰尚
 * 不能修改和删除上面的版权声明
 * 此代码属于江苏丰尚深圳研究院部门编写，在未经允许的情况下不得传播复制
 */
package com.handsome.jay.common;

/**
 * 统一接口响应编码
 *
 * @author Leoly Gu
 * @date 2020-10-23 12:01:33
 */
public enum RtEnum {
    SUCCESS(200, "请求执行成功！"),
    /**
     * 服务器未知异常
     */
    ERROR(503, "请求执行失败！"),
    /**
     * 业务执行异常，带上业务异常提示信息
     */
    FAILURE(500, "请求业务执行异常！"),
    ACCOUNT_OR_PASSWORD_ERROR(501, "账号或者密码不存在！"),
    PERMISSION_DENY(403, "请求资源权限验证失败！"),
    SESSION_TIMEOUT(408, "客户会话已经超时！"),
    PARAMETER_ILLEGAL(412, "请求参数验证失败！"),
    VERSION_ILLEGAL(505, "请求接口版本验证失败！"),
    FLOW_CONTROL(600, "服务被限流！"),
    DEGRADE_CONTROL(601, "服务被降级！"),
    AUTHORITY_CONTROL(602, "无权访问服务！"),
    SYSTEM_CONTROL(603, "触发“系统保护”流控规则！");


    private final int code;
    private final String desc;

    RtEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
