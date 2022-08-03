package com.jairoguo.goods.infra.repository.database.impl;


import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jairo Guo
 */
@Repository("jpa")
public class GoodsRepositoryJpaImpl implements GoodsRepository {


    @Override
    public Boolean save(Goods aggregate) {
        return null;
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
        return null;
    }

    @Override
    public void deductions(GoodsNumber goodsNumber, Long count) {
    }

    @Override
    public SpecsAttribute getSpecs(GoodsNumber goodsNumber) {
        return null;
    }
}
