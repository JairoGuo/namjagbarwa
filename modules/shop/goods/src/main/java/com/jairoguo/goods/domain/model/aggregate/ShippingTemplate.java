package com.jairoguo.goods.domain.model.aggregate;


import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.goods.domain.model.entity.id.ShippingTemplateNumber;
import com.jairoguo.goods.domain.model.value.DeliveryArea;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 运费模板
 *
 * @author Jairo Guo
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShippingTemplate implements AggregateRoot<ShippingTemplateNumber> {

    private ShippingTemplateNumber shippingTemplateNumber;
    /**
     * 运费模版名称
     */
    private String name;
    /**
     * 计费方式
     */
    private String billingType;
    /**
     * 配送区域列表
     */
    private List<DeliveryArea> deliveryAreaList;
    /**
     * 权重
     */
    private Long weight;




}
