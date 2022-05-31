package com.jairoguo.account.domain.service;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.common.base.DomainService;

/**
 * @author Jairo Guo
 */
public interface AccountDomainService extends DomainService {
    /**
     * 创建账户
     *
     * @param account 账户聚合根
     * @return 布尔值
     */
    Account createAccount(Account account);

    Account getAccount(Account account);
}
