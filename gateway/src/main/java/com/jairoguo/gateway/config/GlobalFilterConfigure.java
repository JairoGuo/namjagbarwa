package com.jairoguo.gateway.config;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.jairoguo.auth.dto.RoleTypeEnum;
import com.jairoguo.common.result.Result;
import com.jairoguo.gateway.util.RoleUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jairo Guo
 */
@Configuration
@SuppressWarnings("all")
public class GlobalFilterConfigure {
    @Bean
    public SaReactorFilter getFilter() {

        return new SaReactorFilter()
                // 拦截路由
                .addInclude("/**")
                // 放行账户服务路由
                .addExclude(
                        "/account/signUp/email",
                        "/account/signUp/phone",
                        "/account/signIn/password",
                        "/account/signIn/smsCode",
                        "/auth-server/login/login"
                )
                // 放行sms服务路由
                .addExclude(
                        "/sms/code/get"
                )
                // 认证函数: 每次请求执行
                .setAuth(obj -> {
                            SaRouter.match("/account/getAccount", StpUtil::checkLogin);
                            SaRouter.match("/goods-server/goods/add").check(r ->
                                RoleUtils.checkRole(RoleTypeEnum.SELLER.name(), "该账户不是商家角色"));
                        }

                )
                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    if (e instanceof SaTokenException exception) {
                        return Result.info(String.valueOf(exception.getCode()), exception.getMessage());
                    }
                    return e;
                })
                .setBeforeAuth(r -> {

                });
    }
}
