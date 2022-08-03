package com.jairoguo.seckill.domain.service;

import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;

/**
 * @author Jairo Guo
 */
public interface SeckillGoodsDomainService {

    void addSeckillGoods(SeckillGoods seckillGoods);


    Boolean checkPath(Long userId, Long specsAttributeId, String pathId);

    void deductionStock(Long specsAttributeId);

    SeckillGoods getSeckillGoodsByCache(Long specsAttributeId);

    void markUserSpiked(Long userId, SeckillGoods seckillGoods);

    Boolean isUserSpiked(Long userId, Long specsAttributeId);

    Long getStockByCache(Long specsAttributeId);

    void setSeckillPath(Long userId, Long seckillGoodsId, String pathId);

}
