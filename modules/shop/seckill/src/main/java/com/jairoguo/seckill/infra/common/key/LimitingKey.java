package com.jairoguo.seckill.infra.common.key;

import com.jairoguo.redis.util.RedisKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Component
public class LimitingKey {

    @Resource
    private RedisKey redisKey;

    static final String USER_ID = "uid";

    public String limit(Long userId) {
        return redisKey.getKey("limiting", USER_ID, userId.toString());
    }
}
