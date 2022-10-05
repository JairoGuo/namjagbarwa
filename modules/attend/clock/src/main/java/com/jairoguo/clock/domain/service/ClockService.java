package com.jairoguo.clock.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Jairo Guo
 */
public interface ClockService {

    Boolean clock(Long userId, LocalDateTime dateTime);
    Boolean checkClock(Long userId, LocalDate date);
    Long getClockCount(Long userId, LocalDate date);

    /**
     * 获取用户当月连续签到次数
     * @param userId 用户ID
     * @param date 日期
     * @return 连续签到次数
     */
    int getContinuousClockCount(Long userId, LocalDate date);

    Map<String, Boolean> getClockInfo(Long userId, LocalDate date);



}
