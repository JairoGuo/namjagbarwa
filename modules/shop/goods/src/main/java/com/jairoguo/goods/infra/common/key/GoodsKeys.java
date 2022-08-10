package com.jairoguo.goods.infra.common.key;

import com.jairoguo.redis.util.RedisKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Component
public class GoodsKeys {
    @Resource
    private RedisKey redisKey;

    public String goodsKey(Long goodsId) {
        return redisKey.getKey("goods", "gid", goodsId.toString());
    }

    public String specsKey(Long specsId) {
        return redisKey.getKey("specs", "sid", specsId.toString());
    }

    public String specsStockKey(Long specsId) {
        return redisKey.getKey("specs_stock", "sid", specsId.toString());
    }
}
