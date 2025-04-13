package com.jay.handsome.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jay.handsome.entity.Path;
import com.jay.handsome.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jay
 * @since 2023-04-04
 */
@Mapper
public interface PathDao extends BaseMapper<Path> {
    List<Path> getPathByRoleId(Integer RoleId);
}
