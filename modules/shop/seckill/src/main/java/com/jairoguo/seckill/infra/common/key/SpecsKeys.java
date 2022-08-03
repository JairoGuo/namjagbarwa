package com.jairoguo.seckill.infra.common.key;

import com.jairoguo.redis.util.RedisKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Component
public class SpecsKeys {

    static final String SPECS = "specs";
    static final String STOCK = "stock";
    static final String SPECS_ID = "sid";

    @Resource
    private  RedisKey redisKey;


    public String specsKey(Long specsId) {
        return redisKey.getKey(SPECS,
                SPECS_ID, specsId.toString());
    }


    public String specsStockKey(Long specsId) {
        return redisKey.getKey(STOCK,
                SPECS_ID, specsId.toString());
    }
}
