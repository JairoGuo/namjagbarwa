package com.jairoguo.account.domain.model.entity;

import com.jairoguo.account.domain.model.entity.id.UserId;
import com.jairoguo.common.base.Entity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author Jairo Guo
 */
@Getter
@ToString
public class UserInfo implements Entity<UserId> {

    private UserId userId;
    private String username;
    private String personalizedUrl;
    private LocalDate birthday;
    private String sex;
    private String website;
    private String avatar;

    private UserInfo() {
    }

    public static UserInfo create() {
        return new UserInfo();
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public void bindAvatarUrl(String avatarUrl) {
        this.avatar = avatarUrl;
    }

}
