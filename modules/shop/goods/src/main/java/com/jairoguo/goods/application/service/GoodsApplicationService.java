package com.jairoguo.goods.application.service;

import com.jairoguo.goods.application.bo.GoodsBO;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import com.jairoguo.goods.domain.service.GoodsDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */
@Service
@RequiredArgsConstructor
public class GoodsApplicationService {


    @Resource(name = "mybatis")
    private GoodsRepository goodsRepository;

    @Resource
    private GoodsDomainService goodsDomainService;

    @Transactional
    public void addGoods(GoodsBO goodsBO) {

        goodsDomainService.createGoods(goodsBO.goods());

    }

    public List<Goods> goodsList() {

        return goodsRepository.list();
    }

}
