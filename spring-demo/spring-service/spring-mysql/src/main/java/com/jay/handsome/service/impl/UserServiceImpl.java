package com.jay.handsome.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.handsome.dao.PathDao;
import com.jay.handsome.dao.RoleDao;
import com.jay.handsome.dao.UserDao;
import com.jay.handsome.entity.Path;
import com.jay.handsome.entity.Role;
import com.jay.handsome.entity.User;
import com.jay.handsome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private PathDao pathDao;

    @Override
    public User getByName(String id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,id);
        wrapper.last("limit 1");
        User user = baseMapper.selectOne(wrapper);
        if (user != null){
            List<Role> roleList = roleDao.getRoleByUserId(user.getId());
            user.setRoles(roleList);
            Set<String> paths = new HashSet<>();
            if (roleList!=null){
                for (Role role : roleList) {
                    pathDao.getPathByRoleId(role.getId()).forEach(path -> {
                        paths.add(path.getName());
                    });
                }
            }
            user.setPaths(new ArrayList<>(paths));
        }
        return user;
    }
}
