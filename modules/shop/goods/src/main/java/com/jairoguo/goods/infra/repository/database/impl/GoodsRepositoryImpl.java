package com.jairoguo.goods.infra.repository.database.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.entity.Specs;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.repository.GoodsRepository;
import com.jairoguo.goods.infra.common.key.GoodsKeys;
import com.jairoguo.goods.infra.repository.database.convert.GoodsPOToEntityConvert;
import com.jairoguo.goods.infra.repository.database.convert.GoodsRepositoryConvert;
import com.jairoguo.goods.infra.repository.database.convert.SpecsAttributeRepositoryConvert;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsAttributeMapper;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsDetailMapper;
import com.jairoguo.goods.infra.repository.database.mapper.GoodsMapper;
import com.jairoguo.goods.infra.repository.database.mapper.SpecsAttributeMapper;
import com.jairoguo.goods.infra.repository.database.po.GoodsPO;
import com.jairoguo.goods.infra.repository.database.po.SpecsAttributePO;
import com.jairoguo.redis.util.RedisUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */
@Primary
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

    @Resource
    private RedisTemplate<String, Goods> redisTemplate;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private GoodsKeys goodsKeys;

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

        Goods goodsCache = redisTemplate.opsForValue().get(id.getId().toString());
        if (goodsCache != null) {
            return goodsCache;
        }

        QueryWrapper<GoodsPO> goodsPOQueryWrapper = new QueryWrapper<>();
        goodsPOQueryWrapper.lambda().eq(GoodsPO::getId, id.getId());
        if (id.getId() != null) {
            goodsPOQueryWrapper.lambda().eq(GoodsPO::getId, id.getId());
        }
        if (id.getNumber() != null) {
            goodsPOQueryWrapper.lambda().eq(GoodsPO::getGoodsNumber, id.getNumber());
        }
        GoodsPO goodsPO = goodsMapper.selectOne(goodsPOQueryWrapper);
        Goods goods = GoodsPOToEntityConvert.convertToGoods(goodsPO);

        List<SpecsAttribute> specsAttributeList = getSpecsAttributeList(goodsPO.getId());
        Specs specs = Specs.create();
        specs.setSpecsAttributeList(specsAttributeList);
        goods.setSpecs(specs);
        redisTemplate.opsForValue().set(id.getId().toString(), goods, 10, TimeUnit.MINUTES);

        return goods;
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
            List<SpecsAttribute> specsAttributeList = getSpecsAttributeList(goods.getGoodsNumber().getId());
            Specs specs = Specs.create();
            specs.setSpecsAttributeList(specsAttributeList);
            goods.setSpecs(specs);
        });
        return goodsList;
    }

    @Override
    public void deductions(GoodsNumber goodsNumber, Long count) {
        UpdateWrapper<SpecsAttributePO> specsAttributePOUpdateWrapper = new UpdateWrapper<>();
        specsAttributePOUpdateWrapper.lambda()
                .eq(SpecsAttributePO::getId, goodsNumber.getSpecsAttributeId())
                .ge(SpecsAttributePO::getStock, count)
                .setSql("stock = stock - " + count);

        SpecsAttributePO specsAttributePO = new SpecsAttributePO();

        specsAttributeMapper.update(specsAttributePO, specsAttributePOUpdateWrapper);
    }

    @Override
    public void increase(GoodsNumber goodsNumber, Long count) {
        UpdateWrapper<SpecsAttributePO> specsAttributePOUpdateWrapper = new UpdateWrapper<>();
        specsAttributePOUpdateWrapper.lambda()
                .eq(SpecsAttributePO::getId, goodsNumber.getSpecsAttributeId())
                .setSql("stock = stock + " + count);

        SpecsAttributePO specsAttributePO = new SpecsAttributePO();

        specsAttributeMapper.update(specsAttributePO, specsAttributePOUpdateWrapper);
    }

    @Override
    public SpecsAttribute getSpecs(GoodsNumber goodsNumber) {

        SpecsAttribute specsAttribute = redisUtils.get(
                goodsKeys.specsKey(goodsNumber.getSpecsAttributeId()),
                SpecsAttribute.class);
        if (specsAttribute != null) {
            return specsAttribute;
        }

        SpecsAttributePO specsAttributePO = specsAttributeMapper.selectById(goodsNumber.getSpecsAttributeId());
        SpecsAttribute specs = SpecsAttributeRepositoryConvert.toEntity(specsAttributePO);
        redisUtils.setIfAbsent(goodsKeys.specsKey(goodsNumber.getSpecsAttributeId()), specs, 1L, TimeUnit.MINUTES);
        redisUtils.setIfAbsent(goodsKeys.specsStockKey(goodsNumber.getSpecsAttributeId()), specs.getStock(), 1L, TimeUnit.MINUTES);
        return specs;

    }

    private List<SpecsAttribute> getSpecsAttributeList(Long id) {
        QueryWrapper<SpecsAttributePO> specsAttributePOQueryWrapper = new QueryWrapper<>();
        specsAttributePOQueryWrapper.lambda().eq(SpecsAttributePO::getGoodsId, id);
        List<SpecsAttributePO> specsAttributePOList = specsAttributeMapper.selectList(specsAttributePOQueryWrapper);
        return GoodsPOToEntityConvert.convertToSpecsAttributeList(specsAttributePOList);
    }
}
