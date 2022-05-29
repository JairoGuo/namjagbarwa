package com.jairoguo.account.domain.model.aggregate;

import com.jairoguo.account.domain.model.entity.User;
import com.jairoguo.account.domain.model.entity.id.OpenCode;
import com.jairoguo.common.base.AggregateRoot;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Account implements AggregateRoot<OpenCode> {
    private OpenCode openCode;
    private User user;

    private Account() {
    }

    public static Account create() {
        return new Account();
    }

    public void createUser() {
        this.user = User.create();
    }

    public void bindUser(User user) {
        this.user = user;
    }

    public void setOpenCode(OpenCode openCode) {
        this.openCode = openCode;
    }

    public void setUser(User user) {
        this.bindUser(user);
    }
}
