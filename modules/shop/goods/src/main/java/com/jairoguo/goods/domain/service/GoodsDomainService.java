package com.jairoguo.goods.domain.service;

import com.jairoguo.common.base.DomainService;
import com.jairoguo.goods.domain.model.aggregate.Goods;

/**
 * @author Jairo Guo
 */
public interface GoodsDomainService extends DomainService {
    void createGoods(Goods goods);
}
