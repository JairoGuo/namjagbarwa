package com.jairoguo.account.interfaces.rest.assembler.factory;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.model.entity.User;
import com.jairoguo.account.domain.model.entity.id.OpenCode;
import com.jairoguo.account.domain.model.entity.id.UserId;
import com.jairoguo.common.base.Factory;

/**
 * @author Jairo Guo
 */
public class AccountFactory implements Factory {
    private AccountFactory() {
    }
    public static Account account() {
        return Account.create();
    }

    public static OpenCode openCode() {
        return OpenCode.create();
    }

    public static User user() {
        return User.create();
    }

    public static UserId userId() {
        return UserId.create();
    }
}
