package com.jairoguo.goods.application.service;

import com.jairoguo.common.result.Result;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Jairo Guo
 */

@Service
public class GoodsSpecsApplicationService {

    @Resource(name = "mybatis")
    private GoodsRepository goodsRepository;

    public SpecsAttribute getGoodsSpecs(Long specsAttributeId) {
        GoodsNumber goodsNumber = GoodsNumber.newInstance();
        goodsNumber.setSpecsAttributeId(specsAttributeId);
        SpecsAttribute specs = goodsRepository.getSpecs(goodsNumber);
        if (Optional.ofNullable(specs).isEmpty()) {
            Result.fail("商品规格不存在");
        }
        return specs;
    }
}
