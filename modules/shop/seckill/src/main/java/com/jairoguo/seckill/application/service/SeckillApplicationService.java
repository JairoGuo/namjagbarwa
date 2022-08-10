package com.jairoguo.seckill.application.service;

import cn.hutool.core.lang.UUID;
import com.jairoguo.common.result.Result;
import com.jairoguo.goods.dto.GoodsNumberDTO;
import com.jairoguo.goods.vo.SpecsAttributeVO;
import com.jairoguo.redis.util.RedisKey;
import com.jairoguo.redis.util.RedisUtils;
import com.jairoguo.seckill.application.event.publish.OrderPublish;
import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;
import com.jairoguo.seckill.domain.model.value.SeckillResultEnum;
import com.jairoguo.seckill.domain.repository.SeckillGoodsRepository;
import com.jairoguo.seckill.domain.service.SeckillGoodsDomainService;
import com.jairoguo.seckill.infra.api.AuthApiService;
import com.jairoguo.seckill.infra.api.GoodsApiService;
import com.jairoguo.seckill.infra.common.LocalOverTag;
import com.jairoguo.seckill.infra.common.key.LimitingKey;
import com.jairoguo.seckill.infra.common.key.SeckillPathKeys;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */
@Service
public class SeckillApplicationService {

    @Resource
    private AuthApiService authApiService;

    @Resource
    private GoodsApiService goodsApiService;

    @Resource
    private OrderPublish orderPublish;

    @Resource
    private RedisKey redisKey;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private SeckillGoodsRepository seckillGoodsRepository;

    @Resource
    private SeckillGoodsDomainService seckillGoodsDomainService;

    @Resource
    private LocalOverTag localOverTag;


    @Resource
    private SeckillPathKeys seckillPathKeys;

    @Resource
    private LimitingKey limitingKey;

    @Transactional
    public void seckill(Long specsAttributeId, String pathId) {


        if (Boolean.TRUE.equals(localOverTag.isOver(specsAttributeId))) {
            Result.fail("库存不足");
        }

        // 是否登陆
        Boolean isLogin = authApiService.isLogin();
        Long userId;
        // 验证是否登陆
        if (Boolean.TRUE.equals(isLogin)) {
            userId = authApiService.getUserId();
            if (redisUtils.hasKey(limitingKey.limit(userId))) {
                Integer count = redisUtils.get(limitingKey.limit(userId), Integer.class);
                if (count >= 5) {
                    Result.fail("访问频繁");
                }
                redisUtils.increment(limitingKey.limit(userId));
            } else {
                redisUtils.set(limitingKey.limit(userId), 0, 5L, TimeUnit.SECONDS);

            }

            // 检测秒杀地址
            boolean check = seckillGoodsDomainService.checkPath(userId, specsAttributeId, pathId);
            if (Boolean.FALSE.equals(check)) {
                Result.fail("非法请求");
            }

            SeckillGoods seckillGoods = seckillGoodsDomainService.getSeckillGoodsByCache(specsAttributeId);

            checkTime(seckillGoods.getStartDate(), seckillGoods.getEndDate());

            // 是否已秒杀到
            Boolean isSpiked = seckillGoodsDomainService.isUserSpiked(userId, specsAttributeId);
            if (Boolean.TRUE.equals(isSpiked)) {
                Result.fail("已秒杀到商品");
            }

            // 获取库存
            Long stock = seckillGoodsDomainService.getStockByCache(specsAttributeId);
            if (stock <= 0) {
                localOverTag.setOver(specsAttributeId);
                Result.fail("库存不足");
            }

            // 扣减库存(预减库存)
            seckillGoodsDomainService.deductionStock(specsAttributeId);

            // 调用订单服务完成下单
            orderPublish.placeOrder(userId, specsAttributeId, seckillGoods.getPrice());

            // 标记用户已秒杀到商品
            seckillGoodsDomainService.markUserSpiked(userId, seckillGoods);


        } else {
            Result.fail("用户未登陆");
        }


    }

    @Transactional
    public void add(SeckillGoods seckillGoods) {
        SpecsAttributeVO specsAttribute = goodsApiService.getSpecsAttribute(new GoodsNumberDTO(null, null, seckillGoods.getSpecsAttributeId()));
        if (specsAttribute == null) {
            Result.fail("商品规格不存在");
        }

        // GoodsVO goods = goodsApiService.getGoodsSpecs(
        //         new GoodsNumberDTO(seckillGoods.getGoodsId(), null, seckillGoods.getSpecsAttributeId()));
        // if (goods == null) {
        //     Result.fail("商品不存在");
        // }

        seckillGoodsDomainService.addSeckillGoods(seckillGoods);

    }

    public List<SeckillGoods> goodsList() {
        return seckillGoodsRepository.list();
    }

    public SeckillResultEnum getSeckillResult() {

        return null;
    }

    public String getSeckillPath(Long specsAttributeId) {

        Boolean isLogin = authApiService.isLogin();

        if (Boolean.TRUE.equals(isLogin)) {
            SeckillGoods seckillGoods = seckillGoodsDomainService.getSeckillGoodsByCache(specsAttributeId);

            checkTime(seckillGoods.getStartDate(), seckillGoods.getEndDate());

            Long userId = authApiService.getUserId();

            String pathIdValue = redisUtils.get(seckillPathKeys.pathKey(userId, specsAttributeId), String.class);
            if (pathIdValue != null) {
                return pathIdValue;
            }
            String pathId = UUID.fastUUID().toString();

            seckillGoodsDomainService.setSeckillPath(userId, specsAttributeId, pathId);
            return pathId;
        } else {
            Result.fail("用户未登陆");
        }
        return null;
    }

    private void checkTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (LocalDateTime.now().isBefore(startTime)) {
            Result.fail("秒杀未开始");
        }

        if (LocalDateTime.now().isAfter(endTime)) {
            Result.fail("秒杀已结束");
        }
    }


}


