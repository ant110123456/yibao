package com.yb.dto.createorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("创建订单商品参数对象")
@Data
public class GoodsDto implements Serializable {
    /**
     * 商品的名称
     */
    @ApiModelProperty(value = "商品的名称")
    private String goodsName;
    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述")
    private String goodsDesc;

}
