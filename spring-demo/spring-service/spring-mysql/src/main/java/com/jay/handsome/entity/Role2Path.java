package com.jay.handsome.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role2Path {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
