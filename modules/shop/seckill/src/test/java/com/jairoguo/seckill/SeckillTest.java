package com.jairoguo.seckill;

import cn.hutool.core.lang.UUID;
import com.jairoguo.auth.api.AuthApi;
import com.jairoguo.goods.dto.GoodsNumberDTO;
import com.jairoguo.goods.vo.SpecsAttributeVO;
import com.jairoguo.seckill.infra.api.GoodsApiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */
@SpringBootTest
public class SeckillTest {

    @Resource
    AuthApi authApi;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisScript<Boolean> redisScript;

    @Resource
    private GoodsApiService goodsApiService;


    @Test
    void context() {
        System.out.println(authApi.isLogin());
    }

    @Test
    void context1() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String uuid = UUID.randomUUID().toString();
        Boolean isLock = valueOperations.setIfAbsent("k1", uuid, 20, TimeUnit.SECONDS);
        if (isLock) {
            valueOperations.set("name", "xxx");
            Object name = valueOperations.get("name");
            String value = String.class.cast(name);
            System.out.println(value);
            System.out.println(valueOperations.get("k1"));
            Object k1 = redisTemplate.execute(redisScript, Collections.singletonList("k1"), uuid);
            Boolean k1Value = Boolean.class.cast(k1);
            System.out.println(k1Value);
        } else {
            System.out.println("...");
        }
    }

    @Test
    void test() {
        SpecsAttributeVO specsAttribute = goodsApiService.getSpecsAttribute(new GoodsNumberDTO(null, null, 1542899654519713793L));
        System.out.println(specsAttribute);
    }
}
