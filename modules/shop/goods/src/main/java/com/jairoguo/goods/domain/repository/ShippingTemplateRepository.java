package com.jairoguo.goods.domain.repository;


import com.jairoguo.common.base.Repository;
import com.jairoguo.goods.domain.model.aggregate.ShippingTemplate;
import com.jairoguo.goods.domain.model.entity.id.ShippingTemplateNumber;

/**
 * 运费模版仓储
 *
 * @author Jairo Guo
 */
public interface ShippingTemplateRepository extends Repository<ShippingTemplate, ShippingTemplateNumber> {
}
