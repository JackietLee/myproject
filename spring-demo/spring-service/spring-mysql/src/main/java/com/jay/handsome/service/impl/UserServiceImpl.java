package com.jay.handsome.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.handsome.dao.UserDao;
import com.jay.handsome.entity.User;
import com.jay.handsome.service.UserService;
import org.springframework.stereotype.Service;

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

}
