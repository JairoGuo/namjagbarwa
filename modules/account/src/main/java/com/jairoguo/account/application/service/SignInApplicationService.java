package com.jairoguo.account.application.service;

import com.jairoguo.account.application.api.dto.VerifyCodeDTO;
import com.jairoguo.account.application.api.service.SmsCodeApiService;
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

    @Resource
    private SmsCodeApiService smsCodeApiService;

    @Transactional(rollbackFor = Exception.class)
    public Account login(SignInBO signInBO) {
        Account account = accountDomainService.getAccount(signInBO.getAccount());
        if (account == null) {
            Result.fail("账户不存在");
        } else if (Boolean.TRUE.equals(account.getUser().getState())){
            switch (account.getOpenCode().getType()) {
                case EMAIL -> {
                    Boolean passwordStatus = account.getUser().comparePassword(signInBO.getAccount().getUser().getPassword());
                    if (Boolean.FALSE.equals(passwordStatus)) {
                        Result.fail("密码不正确");
                    }
                }
                case PHONE -> {
                    VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO(account.getOpenCode().getOpenCode(), "LOG_IN", signInBO.getCode());
                    smsCodeApiService.verifySmsCode(verifyCodeDTO);
                }
                default -> Result.fail("不可知的登录类型");


            }
        } else {
            Result.fail("账户被禁用");
        }


        return account;
    }
}
