package com.jairoguo.clock.domain.service.impl;

import com.jairoguo.clock.domain.service.ClockService;
import com.jairoguo.clock.infra.common.key.ClockKeys;
import com.jairoguo.redis.util.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Jairo Guo
 */
@Service
public class ClockServiceImpl implements ClockService {

    @Resource

    private RedisUtils redisUtils;

    @Resource
    private ClockKeys clockKeys;


    @Override
    public Boolean clock(Long userId, LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        String key = clockKeys.clockKey(userId, date);
        int dayOffsetOfMonth = date.getDayOfMonth() - 1;
        return redisUtils.setBit(key, dayOffsetOfMonth, true);
    }

    @Override
    public Boolean checkClock(Long userId, LocalDate date) {
        String key = clockKeys.clockKey(userId, date);
        return redisUtils.getBit(key, date.getDayOfMonth() - 1);
    }

    @Override
    public Long getClockCount(Long userId, LocalDate date) {
        String key = clockKeys.clockKey(userId, date);
        return redisUtils.bitCount(key);
    }

    @Override
    public int getContinuousClockCount(Long userId, LocalDate date) {
        String key = clockKeys.clockKey(userId, date);

        List<Long> list = redisUtils.bitField(key, date.getDayOfMonth(), 0);
        // AtomicInteger count = new AtomicInteger();
        final int[] count = {0};

        Optional.ofNullable(list).ifPresent(l -> {
            if (!l.isEmpty()) {
                Long value;
                value = (value = l.get(0)) == null ? 0 : value;
                for (int i = 0; i < date.getDayOfMonth(); i++) {
                    // 先右移在左移，如果与原值相同代表低位为0
                    if (value.equals(value >> 1 << 1)) {
                        if (i > 0) break;
                    } else {
                        // count.getAndIncrement();
                        count[0]++;
                    }
                    value >>= 1;
                }
            }
        });
        return count[0];
    }

    @Override
    public Map<String, Boolean> getClockInfo(Long userId, LocalDate date) {
        Map<String, Boolean> clockMap = new LinkedHashMap<>(date.getDayOfMonth());
        String key = clockKeys.clockKey(userId, date);
        List<Long> list = redisUtils.bitField(key, date.lengthOfMonth(), 0);
        Optional.ofNullable(list).ifPresent(l -> {
            if (!l.isEmpty()) {
                Long value;
                value = (value = l.get(0)) == null ? 0 : value;
                for (int i = date.lengthOfMonth(); i > 0; i--) {
                    LocalDate dayOfMonth = date.withDayOfMonth(i);
                    clockMap.put(dayOfMonth.toString(), value >> 1 << 1 != value);
                    value >>= 1;
                }
            }
        });
        return clockMap;
    }
}
