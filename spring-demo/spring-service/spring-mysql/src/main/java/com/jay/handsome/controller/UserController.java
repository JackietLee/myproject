package com.jay.handsome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.handsome.jay.common.Rt;
import com.jay.handsome.entity.User;
import com.jay.handsome.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jay
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询所有接口
     * @return
     */
    @GetMapping
    public Rt<List<User>> list(){
        return Rt.ok(userService.list());
    }

    /**
     * 根据id查询数据接口
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Rt<User> getById(@PathVariable Integer id){
        return Rt.ok(userService.getById(id));
    }

    /**
     * 根据id查询数据接口
     * @param id
     * @return
     */
    @GetMapping("/{name}")
    public Rt<User> getByName(@PathVariable Integer id){
        return Rt.ok(userService.getByName(id));
    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Rt<IPage<User>> page(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        return Rt.ok(userService.page(new Page<User>(pageNum,pageSize),queryWrapper));
    }

    /**
     * 新增和更新接口
     * @param user
     * @return
     */
    @PostMapping
    public Rt<User> saveOrUpdate(@RequestBody User user){
        userService.saveOrUpdate(user);
        return Rt.ok(user);
    }
    /**
     * 删除接口
     * @param id
     * @return
     */
    @PostMapping("/del/{id}")
    public Rt<Boolean> delete(@PathVariable Integer id){
        return Rt.ok(userService.removeById(id));
    }
    /**
     * 批量删除接口
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Rt<Boolean> deleteBatch(@RequestBody List<Integer> ids){
        return Rt.ok(userService.removeByIds(ids));
    }

}