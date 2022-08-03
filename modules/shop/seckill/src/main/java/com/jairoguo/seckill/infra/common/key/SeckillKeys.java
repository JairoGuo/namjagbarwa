package com.jairoguo.seckill.infra.common.key;

import com.jairoguo.redis.util.RedisKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Component
public class SeckillKeys {

    @Resource
    private RedisKey redisKey;

    static final String KILLED = "killed";
    static final String SPECS_ID = "sid";

    static final String USER_ID = "uid";

    public String killed(Long userId, Long specsId) {
        return redisKey.getKey("killed",
                USER_ID, userId.toString(), SPECS_ID, specsId.toString());
    }


}
