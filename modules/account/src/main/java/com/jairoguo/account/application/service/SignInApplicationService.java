package com.jairoguo.account.application.service;

import com.jairoguo.account.application.bo.SignInBO;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.service.AccountDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class SignInApplicationService {

    @Resource
    private AccountDomainService accountDomainService;

    @Transactional(rollbackFor = Exception.class)
    public Account login(SignInBO signInBO) {

        return accountDomainService.verify(signInBO.account());
    }
}
