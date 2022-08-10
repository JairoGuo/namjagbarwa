package com.jairoguo.gateway.util;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;

/**
 * @author Jairo Guo
 */
public class RoleUtils {

    public static Boolean checkRole(String roleName) {

        if (Boolean.FALSE.equals(SaStrategy.me.hasElement.apply(StpUtil.getRoleList(), roleName))) {
            throw new SaTokenException(-1, "不存在该角色");
        }
        return true;
    }

    public static Boolean checkRole(String roleName, String msg) {

        if (Boolean.FALSE.equals(SaStrategy.me.hasElement.apply(StpUtil.getRoleList(), roleName))) {
            throw new SaTokenException(-1, msg);
        }
        return true;
    }
}
