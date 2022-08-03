package com.jairoguo.goods.application.service;

import com.jairoguo.common.result.Result;
import com.jairoguo.goods.application.bo.GoodsBO;
import com.jairoguo.goods.application.event.publish.OrderPublish;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import com.jairoguo.goods.domain.service.GoodsDomainService;
import com.jairoguo.goods.infra.api.AuthApiService;
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

    @Resource
    private AuthApiService authApiService;

    @Resource
    private OrderPublish orderPublish;

    @Transactional
    public void addGoods(GoodsBO goodsBO) {

        goodsDomainService.createGoods(goodsBO.goods());

    }

    public Goods getGoods(Long goodsId, Long number) {
        GoodsNumber goodsNumber = GoodsNumber.newInstance();
        goodsNumber.setId(goodsId);
        goodsNumber.setNumber(number);

        Goods goods = goodsRepository.findById(goodsNumber);
        if (goods == null) {
            Result.fail("商品不存在");
        }
        return goods;
    }

    public List<Goods> goodsList() {

        return goodsRepository.list();
    }

    public void deductions(Long specsAttributeId, Long count) {
        goodsDomainService.deductions(specsAttributeId, count);

    }

    public void buy(Long goodsId, Long specsAttributeId, Long number) {
        Boolean isLogin = authApiService.isLogin();
        Long userId;
        // 验证是否登陆
        if (Boolean.TRUE.equals(isLogin)) {

            userId = authApiService.getUserId();

            // 查询商品是否有库存
            GoodsNumber goodsNumber = GoodsNumber.newInstance();
            goodsNumber.setId(goodsId);
            goodsNumber.setSpecsAttributeId(specsAttributeId);
            SpecsAttribute specsAttribute = goodsRepository.getSpecs(goodsNumber);
            Long stock = specsAttribute.getStock();
            if (stock <= 0 || number > stock) {
                Result.fail("库存不足");
            }

            // 调用领域服务完成下单
            orderPublish.placeOrder(userId, specsAttributeId, number);

        } else {
            Result.fail("用户未登陆");
        }
    }


}
