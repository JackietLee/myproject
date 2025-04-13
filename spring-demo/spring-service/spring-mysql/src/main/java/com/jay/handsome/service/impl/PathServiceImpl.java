package com.jay.handsome.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.handsome.dao.PathDao;
import com.jay.handsome.dao.RoleDao;
import com.jay.handsome.entity.Path;
import com.jay.handsome.entity.Role;
import com.jay.handsome.service.PathService;
import com.jay.handsome.service.RoleService;
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
public class PathServiceImpl extends ServiceImpl<PathDao, Path> implements PathService {

}
