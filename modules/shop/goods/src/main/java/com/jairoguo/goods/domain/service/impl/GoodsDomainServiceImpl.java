package com.jairoguo.goods.domain.service.impl;

import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import com.jairoguo.goods.domain.service.GoodsDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class GoodsDomainServiceImpl implements GoodsDomainService {

    @Resource(name = "mybatis")
    private GoodsRepository goodsRepository;

    @Override
    public void createGoods(Goods goods) {
        goodsRepository.save(goods);
    }
}
