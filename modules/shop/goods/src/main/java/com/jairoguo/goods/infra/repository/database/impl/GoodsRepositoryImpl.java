package com.jairoguo.goods.infra.repository.database.impl;


import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import com.jairoguo.goods.infra.repository.database.assembler.GoodsPersistentAssembler;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsAttributeMapper;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsDetailMapper;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsMapper;
import com.jairoguo.goods.infra.repository.database.po.GoodsAttributePO;
import com.jairoguo.goods.infra.repository.database.po.GoodsPO;
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
    private GoodsPersistentAssembler productPersistentAssembler;

    @Override
    public Boolean save(Goods goods) {
        // 持久化商品
        GoodsPO goodsPO = GoodsPersistentAssembler.INSTANCE.toProductPO(goods);
        int insertStatus = goodsMapper.insert(goodsPO);
        if (insertStatus != 0) {
            // 持久化商品属性
            if (Optional.ofNullable(goods.getAttributeList()).isPresent()) {
                for (GoodsAttributePO goodsAttributePO :
                        productPersistentAssembler.toProductAttributeListPO(goods.getAttributeList())) {
                    goodsAttributePO.setGoodsId(goodsPO.getId());
                    goodsAttributeMapper.insert(goodsAttributePO);
                }
                goodsDetailMapper.insert(productPersistentAssembler.toProductDetailPO(goods.getDetail()));

            }

            // 持久化商品详情
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
        return GoodsPersistentAssembler.INSTANCE.toGoodsList(goodsListPO);
    }
}
