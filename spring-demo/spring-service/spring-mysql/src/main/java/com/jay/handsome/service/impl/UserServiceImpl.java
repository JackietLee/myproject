package com.jay.handsome.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.handsome.dao.RoleDao;
import com.jay.handsome.dao.UserDao;
import com.jay.handsome.entity.Role;
import com.jay.handsome.entity.User;
import com.jay.handsome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay
 * @since 2023-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public User getByName(Integer id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,id);
        wrapper.last("limit 1");
        User user = baseMapper.selectOne(wrapper);
        if (user != null){
            List<Role> roleList = roleDao.getRoleByUserId(user.getId());
        }
        return null;
    }
}
