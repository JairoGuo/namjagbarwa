package com.jairoguo.account.domain.repository;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.model.entity.id.OpenCode;
import com.jairoguo.common.base.Repository;

/**
 * @author Jairo Guo
 */
public interface AccountRepository extends Repository<Account, OpenCode> {
}
