package com.jairoguo.account.domain.service.impl;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.repository.AccountRepository;
import com.jairoguo.account.domain.service.AccountDomainService;
import com.jairoguo.common.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Jairo Guo
 */
@Service
public class AccountDomainServiceImpl implements AccountDomainService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        Account accountRlt = accountRepository.findById(account.getOpenCode());
        // 账户不存在
        if (Optional.ofNullable(accountRlt).isEmpty()) {
            account.getUser().encryption();
            accountRepository.save(account);
        } else {
            Result.fail("账户已存在");
            return null;
        }

        return account;
    }

    @Override
    public Account verify(Account account) {
        Account accountRlt = accountRepository.findById(account.getOpenCode());
        if (Optional.ofNullable(accountRlt).isPresent()) {
            Boolean passwordStatus = accountRlt.getUser().comparePassword(account.getUser().getPassword());
            if (Boolean.FALSE.equals(passwordStatus)) {
                Result.fail("密码不正确");
            }
        } else {
            Result.fail("账户不存在");
        }
        return accountRlt;
    }
}
