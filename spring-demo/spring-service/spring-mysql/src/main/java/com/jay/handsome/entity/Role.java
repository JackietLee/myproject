package com.jay.handsome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jay
 * @since 2023-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@ApiModel(value="User对象", description="")
@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    private String  name;

}
