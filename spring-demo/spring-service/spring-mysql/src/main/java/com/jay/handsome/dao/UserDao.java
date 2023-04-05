package com.jay.handsome.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jay.handsome.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jay
 * @since 2023-04-04
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
