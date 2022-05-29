package com.jairoguo.account.application.service;

import com.jairoguo.account.application.bo.SignUpBO;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.service.AccountDomainService;
import com.jairoguo.common.base.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class SignUpApplicationService implements ApplicationService {

    @Resource
    private AccountDomainService accountDomainService;

    @Transactional(rollbackFor = Exception.class)
    public Account register(SignUpBO signUpBO) {

        return accountDomainService.createAccount(signUpBO.account());
    }

}
