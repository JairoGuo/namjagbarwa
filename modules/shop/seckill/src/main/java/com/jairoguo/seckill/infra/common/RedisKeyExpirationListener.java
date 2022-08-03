package com.jairoguo.seckill.infra.common;

import com.jairoguo.redis.util.RedisKey;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Resource
    private LocalOverTag localOverTag;

    @Resource
    private RedisKey redisKey;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (message.toString().startsWith(
                redisKey.getKey("goods", "gid"))) {
            String[] keyItems = message.toString().split(":");
            String businessGoodsId = keyItems[3];
            localOverTag.removeTag(Long.parseLong(businessGoodsId));
        }

    }
}