/*
 * Copyright (c) 2020-2030 深圳丰尚智慧农牧科技公司
 * 不能修改和删除上面的版权声明
 * 此代码属于深圳丰尚智慧农牧研发中心部门编写，在未经允许的情况下不得传播复制
 */
package com.jay.handsome.feign;


import com.handsome.jay.common.Rt;
import com.jay.handsome.entity.User;
import com.jay.handsome.vo.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 请加入注释
 *
 * @author AndrewYan
 * @date 2022-05-25 17:30:57
 */
@FeignClient(value = "mysql", path = "/user", contextId = "user", fallbackFactory =  UserInfoFallbackFactory.class)
public interface UserInfoFeign {

    /**
     * 根据令牌获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/token/info")
    Rt<UserInfoVO> getUserInfo(@RequestHeader(name = "token") String token);


    @GetMapping("/{id}")
    public Rt<User> getById(@PathVariable("id") Integer id);
}
