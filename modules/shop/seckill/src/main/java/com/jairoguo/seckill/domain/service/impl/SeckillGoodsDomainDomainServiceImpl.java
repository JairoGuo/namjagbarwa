package com.jairoguo.seckill.domain.service.impl;

import com.jairoguo.common.result.Result;
import com.jairoguo.redis.util.RedisKey;
import com.jairoguo.redis.util.RedisUtils;
import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;
import com.jairoguo.seckill.domain.repository.SeckillGoodsRepository;
import com.jairoguo.seckill.domain.service.SeckillGoodsDomainService;
import com.jairoguo.seckill.infra.common.LocalOverTag;
import com.jairoguo.seckill.infra.common.key.SeckillKeys;
import com.jairoguo.seckill.infra.common.key.SeckillPathKeys;
import com.jairoguo.seckill.infra.common.key.SpecsKeys;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */
@Service
public class SeckillGoodsDomainDomainServiceImpl implements SeckillGoodsDomainService {

    @Resource
    private SeckillGoodsRepository seckillGoodsRepository;

    @Resource
    private RedisKey redisKey;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private LocalOverTag localOverTag;

    static final String STOCK = "stock";

    @Resource
    private SpecsKeys specsKeys;

    @Resource
    private SeckillPathKeys seckillPathKeys;

    @Resource
    private SeckillKeys seckillKeys;


    @Override
    public void addSeckillGoods(SeckillGoods seckillGoods) {
        if (seckillGoods.getStartDate().isBefore(LocalDateTime.now())) {
            Result.fail("秒杀时间不能小于当前时间");
        }
        seckillGoodsRepository.save(seckillGoods);

        Long time = Duration.between(LocalDateTime.now(), seckillGoods.getStartDate()).toMillis()
                + Duration.between(seckillGoods.getStartDate(), seckillGoods.getEndDate()).toMillis();
        // 缓存秒杀商品
        redisUtils.set(
                specsKeys.specsKey(seckillGoods.getSpecsAttributeId()),
                seckillGoods, time);
        // 缓存秒杀商品库存
        redisUtils.set(
                specsKeys.specsStockKey(seckillGoods.getSpecsAttributeId()),
                seckillGoods.getStock(), time);
    }

    @Override
    public Boolean checkPath(Long userId, Long specsAttributeId, String pathId) {

        String pathIdValue = redisUtils.get(seckillPathKeys.pathKey(userId, specsAttributeId), String.class);
        if (pathIdValue != null) {
            return pathIdValue.equals(pathId);

        }
        return false;
    }

    @Override
    public void deductionStock(Long specsAttributeId) {
        // redisUtils.decrement(redisKey.getKey(STOCK, "said", seckillGoodsId.toString()));
        // 使用lua脚本预减库存
        Long stock = redisUtils.execute("stock.lua",
                Collections.singletonList(specsKeys.specsStockKey(specsAttributeId)),
                Long.class);
        if (stock <= 0) {
            localOverTag.setOver(specsAttributeId);
            Result.fail("库存不足");
        }
    }

    @Override
    public SeckillGoods getSeckillGoodsByCache(Long specsAttributeId) {
        SeckillGoods seckillGoods = redisUtils.get(
                specsKeys.specsKey(specsAttributeId),
                SeckillGoods.class);

        if (seckillGoods == null) {
            Result.fail("秒杀商品不存在或秒杀已结束");
        }

        return seckillGoods;
    }

    @Override
    public void markUserSpiked(Long userId, SeckillGoods seckillGoods) {
        long endTime = Duration.between(LocalDateTime.now(), seckillGoods.getEndDate()).toMillis();
        redisUtils.set(seckillKeys.killed(userId, seckillGoods.getSpecsAttributeId()),
                true, endTime);
    }

    @Override
    public Boolean isUserSpiked(Long userId, Long specsAttributeId) {
        return redisUtils.hasKey(seckillKeys.killed(userId, specsAttributeId));
    }

    @Override
    public Long getStockByCache(Long specsAttributeId) {
        return redisUtils.get(specsKeys.specsStockKey(specsAttributeId), Long.class);
    }

    @Override
    public void setSeckillPath(Long userId, Long specsAttributeId, String pathId) {
        redisUtils.set(seckillPathKeys.pathKey(userId, specsAttributeId),
                pathId, 60L, TimeUnit.SECONDS);
    }

}
