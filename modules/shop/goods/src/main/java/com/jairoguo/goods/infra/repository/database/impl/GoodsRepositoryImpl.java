package com.jairoguo.goods.infra.repository.database.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.Specs;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import com.jairoguo.goods.infra.repository.database.convert.GoodsPOToEntityConvert;
import com.jairoguo.goods.infra.repository.database.convert.GoodsRepositoryConvert;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsAttributeMapper;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsDetailMapper;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsMapper;
import com.jairoguo.goods.infra.repository.database.mapper.SpecsAttributeMapper;
import com.jairoguo.goods.infra.repository.database.po.GoodsPO;
import com.jairoguo.goods.infra.repository.database.po.SpecsAttributePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author Jairo Guo
 */
@Repository("mybatis")
public class GoodsRepositoryImpl implements GoodsRepository {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsAttributeMapper goodsAttributeMapper;

    @Resource
    private GoodsDetailMapper goodsDetailMapper;

    @Resource
    private SpecsAttributeMapper specsAttributeMapper;

    @Override
    public Boolean save(Goods goods) {
        // 持久化商品
        GoodsPO goodsPO = GoodsRepositoryConvert.convertToGoodsPO(goods);
        int insertStatus = goodsMapper.insert(goodsPO);
        if (insertStatus != 0) {
            /*
            持久化商品规格
             */
            Optional.ofNullable(goods.getSpecs().getSpecsAttributeList())
                    .ifPresent(specsAttr ->
                            specsAttr.forEach(attr -> {
                                SpecsAttributePO specsAttributePO = GoodsRepositoryConvert.convertToSpecsAttributePO(attr);
                                specsAttributePO.setGoodsId(goodsPO.getId());
                                specsAttributeMapper.insert(specsAttributePO);
                            }));
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Goods findById(GoodsNumber id) {
        return null;
    }

    @Override
    public Boolean delete(GoodsNumber id) {
        return null;
    }

    @Override
    public Boolean update(Goods aggregate) {
        return null;
    }


    @Override
    public List<Goods> list() {
        List<GoodsPO> goodsListPO = goodsMapper.selectList(null);

        List<Goods> goodsList = GoodsPOToEntityConvert.convertToGoodsList(goodsListPO);
        goodsList.forEach(goods -> {
            QueryWrapper<SpecsAttributePO> specsAttributePOQueryWrapper = new QueryWrapper<>();
            specsAttributePOQueryWrapper.lambda().eq(SpecsAttributePO::getGoodsId, goods.getGoodsNumber().getId());
            List<SpecsAttributePO> specsAttributePOList = specsAttributeMapper.selectList(specsAttributePOQueryWrapper);
            List<SpecsAttribute> specsAttributeList = GoodsPOToEntityConvert.convertToSpecsAttributeList(specsAttributePOList);
            Specs specs = Specs.create();
            specs.setSpecsAttributeList(specsAttributeList);
            goods.setSpecs(specs);
        });
        return goodsList;
    }
}
