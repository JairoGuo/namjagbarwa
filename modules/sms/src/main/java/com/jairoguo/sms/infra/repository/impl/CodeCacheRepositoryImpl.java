package com.jairoguo.sms.infra.repository.impl;

import com.jairoguo.common.result.Result;
import com.jairoguo.redis.util.RedisKey;
import com.jairoguo.redis.util.RedisUtil;
import com.jairoguo.sms.domain.model.aggregate.PhoneCode;
import com.jairoguo.sms.domain.model.entity.id.Phone;
import com.jairoguo.sms.domain.model.value.CodeUseEnum;
import com.jairoguo.sms.domain.repository.CodeCacheRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class CodeCacheRepositoryImpl implements CodeCacheRepository {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisKey redisKey;

    @Override
    public Boolean save(PhoneCode aggregate) {
        return null;
    }

    private String getUse(CodeUseEnum useEnum) {
        switch (Optional.ofNullable(useEnum).orElseGet(() -> {
            Result.fail("useType为null");
            return null;
        })) {
            case REGISTER -> {
                return "reg";
            }
            case VALID -> {
                return "val";
            }
            case LOG_IN -> {
                return "loin";
            }
            default -> Result.fail("使用不存在的用途类型");
        }
        return null;

    }

    @Override
    public String getCode(PhoneCode phoneCode) {
        return redisUtil.get(
                redisKey.getKey(
                        getUse(phoneCode.getUse()), phoneCode.getPhone().getPhone()),
                String.class);

    }

    @Override
    public void delete(PhoneCode phoneCode) {
        String key = redisKey.getKey(getUse(phoneCode.getUse()), phoneCode.getPhone().getPhone());
        redisUtil.delete(key);
    }

    @Override
    public PhoneCode findById(Phone id) {
        return null;
    }

    @Override
    public Boolean delete(Phone id) {
        return null;
    }

    @Override
    public Boolean update(PhoneCode aggregate) {
        return null;
    }

    @Override
    public void save(PhoneCode phoneCode, Long time, TimeUnit timeUnit) {
        String key = redisKey.getKey(getUse(phoneCode.getUse()), phoneCode.getPhone().getPhone());
        redisUtil.set(key, phoneCode.getCode().getCode(),time, timeUnit);
    }
}
