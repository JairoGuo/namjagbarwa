package com.jairoguo.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultCodeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jairo Guo
 */
@Configuration
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
                .setAuth(obj -> SaRouter.match("/account/getAccount", StpUtil::checkLogin))
                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> Result.info(ResultCodeEnum.INFO, e.getMessage()))
                .setBeforeAuth(r -> {
                });
    }
}
