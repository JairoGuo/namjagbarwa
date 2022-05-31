package com.jairoguo.account.application.service;

import com.jairoguo.account.application.bo.SignInBO;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.service.AccountDomainService;
import com.jairoguo.common.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class SignInApplicationService {

    @Resource
    private AccountDomainService accountDomainService;

    @Transactional(rollbackFor = Exception.class)
    public Account login(SignInBO signInBO) {
        Account account = accountDomainService.getAccount(signInBO.account());
        if (account == null) {
            Result.fail("账户不存在");
        } else if (Boolean.TRUE.equals(account.getUser().getState())){
            switch (account.getOpenCode().getType()) {
                case EMAIL -> {
                    Boolean passwordStatus = account.getUser().comparePassword(account.getUser().getPassword());
                    if (Boolean.FALSE.equals(passwordStatus)) {
                        Result.fail("密码不正确");
                    }
                }
                case PHONE -> {
                    // TODO: 验证手机号

                }
                default -> Result.fail("不可知的登录类型");


            }
        } else {
            Result.fail("账户被禁用");
        }


        return account;
    }
}
