package com.jairoguo.clock.infra.common.key;

import com.jairoguo.redis.util.RedisKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author Jairo Guo
 */

@Component
public class ClockKeys {

    @Resource

    private RedisKey redisKey;

    static final String CLOCK_BU = "clock";
    static final String USER_ID = "u";
    static final String TIME = "t";


    public String clockKey(Long userId, LocalDate date) {
        String dateByMonth = String.format("%d-%d", date.getYear(), date.getMonthValue());
        return redisKey.getKey(CLOCK_BU,
                USER_ID, userId.toString(),
                TIME, dateByMonth);
    }


}
