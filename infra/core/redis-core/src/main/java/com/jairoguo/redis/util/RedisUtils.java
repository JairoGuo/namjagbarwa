package com.jairoguo.redis.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */
@Component
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public <T> boolean set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);

        return true;
    }

    public <T> boolean set(String key, T value, Long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }

        return true;
    }

    public <T> boolean set(String key, T value, Long time, TimeUnit timeUnit) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }

        return true;
    }

    public Object get(String key) {

        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        if (clazz == Long.class) {
            Number value = (Number) get(key);
            return clazz.cast(value.longValue());
        }
        return clazz.cast(get(key));
    }

    public <T> List<T> getList(String key, Class<T> clazz) {

        if (get(key) instanceof List<?> list) {
            if (list.isEmpty()) {
                return Collections.emptyList();
            }
            return list.stream().map(clazz::cast).toList();
        }

        return Collections.emptyList();
    }

    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void increment(String key, Long number) {
        redisTemplate.opsForValue().increment(key, number);
    }

    public void decrement(String key) {
        redisTemplate.opsForValue().decrement(key);
    }

    public void decrement(String key, Long delta) {
        redisTemplate.opsForValue().decrement(key, delta);
    }

    public boolean setBit(String key, long offset, boolean value) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setBit(key, offset, value));
    }

    public boolean getBit(String key, long offset) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().getBit(key, offset));
    }

    public Long bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.bitCount(key.getBytes()));
    }

    public List<Long> bitField(String key, int bits, long offset) {
        return redisTemplate.execute(
                (RedisCallback<List<Long>>) connection ->
                        connection.bitField(
                                key.getBytes(),
                                BitFieldSubCommands
                                        .create()
                                        .get(BitFieldSubCommands.BitFieldType.unsigned(bits))
                                        .valueAt(offset)));
    }

    public <T> void setIfAbsent(String key, T value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    public <T> void setIfAbsent(String key, T value, Long time) {
        redisTemplate.opsForValue().setIfAbsent(key, value, time, TimeUnit.MILLISECONDS);
    }

    public <T> void setIfAbsent(String key, T value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public <T> T execute(String file, List<String> keys, Class<T> clazz) {

        DefaultRedisScript<T> redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource(file));
        redisScript.setResultType(clazz);

        return redisTemplate.execute(redisScript, keys, Collections.emptyList());

    }

    public <T> T execute(String file, List<String> keys, Class<T> clazz, Object... args) {

        DefaultRedisScript<T> redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource(file));
        redisScript.setResultType(clazz);

        return redisTemplate.execute(redisScript, keys, args);

    }

    public Boolean lock(String key) {
        return lock(key, 30L, TimeUnit.SECONDS);
    }

    public Boolean lock(String key, Long time) {
        return lock(key, time, TimeUnit.MILLISECONDS);
    }

    public Boolean lock(String key, Long time, TimeUnit timeUnit) {
        Long result = execute("lock.lua", Collections.singletonList(key), Long.class, Thread.currentThread().getId(), timeUnit.convert(time, timeUnit));
        return result != null && result.intValue() == 1;
    }

    public Boolean unlock(String key) {
        execute("unlock.lua", Collections.singletonList(key), Long.class, Thread.currentThread().getId());
        return true;
    }
}
