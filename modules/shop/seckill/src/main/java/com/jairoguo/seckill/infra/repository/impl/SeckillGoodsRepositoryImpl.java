package com.jairoguo.seckill.infra.repository.impl;

import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;
import com.jairoguo.seckill.domain.model.entity.id.SeckillGoodsNumber;
import com.jairoguo.seckill.domain.repository.SeckillGoodsRepository;
import com.jairoguo.seckill.infra.repository.convert.SeckillGoodsRepositoryConvert;
import com.jairoguo.seckill.infra.repository.mapper.SeckillGoodsMapper;
import com.jairoguo.seckill.infra.repository.po.SeckillGoodsPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */
@Repository
public class SeckillGoodsRepositoryImpl implements SeckillGoodsRepository {

    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;

    @Override
    public Boolean save(SeckillGoods aggregate) {
        SeckillGoodsPO seckillGoodsPO = SeckillGoodsRepositoryConvert.convertToSeckillGoodsPO(aggregate);
        int count = seckillGoodsMapper.insert(seckillGoodsPO);
        return count != 0;
    }

    @Override
    public SeckillGoods findById(SeckillGoodsNumber id) {
        return null;
    }

    @Override
    public Boolean delete(SeckillGoodsNumber id) {
        return null;
    }

    @Override
    public Boolean update(SeckillGoods aggregate) {
        return null;
    }


    @Override
    public List<SeckillGoods> list() {
        List<SeckillGoodsPO> list = seckillGoodsMapper.selectList(null);
        return SeckillGoodsRepositoryConvert.convertToList(list);
    }
}
