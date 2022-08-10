package com.jairoguo.gateway.config;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.jairoguo.redis.util.RedisKey;
import com.jairoguo.redis.util.RedisUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */

@Component
public class AuthData implements StpInterface {


    @Lazy
    @Resource
    private RoleApiService roleApiService;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private RedisKey redisKey;

    @Override
    public List<String> getPermissionList(Object o, String s) {

        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        List<String> roleList;
        redisKey.setModule("auth");
        roleList = redisUtils.getList(
                redisKey.getKey("roleList",
                        "userId", loginId.toString()), String.class);
        if (roleList.isEmpty()) {
            try {
                roleList = roleApiService.getRoleList(StpUtil.getLoginIdAsLong());

            } catch (Exception e) {
                throw new SaTokenException("调用认证服务失败");
            }
            redisUtils.set(
                    redisKey.getKey("roleList",
                            "userId", loginId.toString()),
                    roleList, 5L, TimeUnit.MINUTES);
        }
        return roleList;
    }
}
