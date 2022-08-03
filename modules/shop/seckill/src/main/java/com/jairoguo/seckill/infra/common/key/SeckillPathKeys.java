package com.jairoguo.seckill.infra.common.key;

import com.jairoguo.redis.util.RedisKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Component

public class SeckillPathKeys {

    static final String SECKILL_PATH = "seckill-path";

    static final String SPECS_ID = "sid";

    static final String USER_ID = "uid";


    @Resource
    private RedisKey redisKey;

    public String pathKey(Long userId, Long sspecsId) {
        return redisKey.getKey(SECKILL_PATH,
                USER_ID, userId.toString(),
                SPECS_ID, sspecsId.toString());
    }
}
