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
    public Account getAccount(Account account) {
        return accountRepository.findById(account.getOpenCode());
    }
}
