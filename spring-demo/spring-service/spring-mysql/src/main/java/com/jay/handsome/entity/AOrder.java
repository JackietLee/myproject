package com.jay.handsome.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * a订单
 * </p>
 *
 * @author jay
 * @since 2024-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AOrder对象", description="a订单")
public class AOrder implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer count;

    private BigDecimal totalPrice;


}
