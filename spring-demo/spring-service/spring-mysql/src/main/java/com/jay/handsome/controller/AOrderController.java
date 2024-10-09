package com.jay.handsome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.handsome.jay.common.Rt;
import com.jay.handsome.entity.AOrder;
import com.jay.handsome.entity.AOrder;
import com.jay.handsome.service.AOrderService;
import com.jay.handsome.service.AOrderService;
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
@RequestMapping("/a")
public class AOrderController {

    @Resource
    private AOrderService aOrderService;

    /**
     * 查询所有接口
     * @return
     */
    @GetMapping
    public Rt<List<AOrder>> list(){
        return Rt.ok(aOrderService.list());
    }

    /**
     * 根据id查询数据接口
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Rt<AOrder> getById(@PathVariable Integer id){
        return Rt.ok(aOrderService.getById(id));
    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Rt<IPage<AOrder>> page(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        QueryWrapper<AOrder> queryWrapper=new QueryWrapper<AOrder>();
        return Rt.ok(aOrderService.page(new Page<AOrder>(pageNum,pageSize),queryWrapper));
    }

    /**
     * 新增和更新接口
     * @param AOrder
     * @return
     */
    @PostMapping
    public Rt<AOrder> saveOrUpdate(@RequestBody AOrder AOrder){
        aOrderService.saveOrUpdate(AOrder);
        return Rt.ok(AOrder);
    }
    /**
     * 删除接口
     * @param id
     * @return
     */
    @PostMapping("/del/{id}")
    public Rt<Boolean> delete(@PathVariable Integer id){
        return Rt.ok(aOrderService.removeById(id));
    }
    /**
     * 批量删除接口
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Rt<Boolean> deleteBatch(@RequestBody List<Integer> ids){
        return Rt.ok(aOrderService.removeByIds(ids));
    }

}