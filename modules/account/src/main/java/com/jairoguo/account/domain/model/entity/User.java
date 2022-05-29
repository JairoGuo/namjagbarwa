package com.jairoguo.account.domain.model.entity;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.jairoguo.account.domain.model.entity.id.UserId;
import com.jairoguo.common.base.Entity;
import lombok.Getter;
import lombok.ToString;


/**
 * @author Jairo Guo
 */
@Getter
@ToString
public class User implements Entity<UserId> {
    private UserId userId;
    private Boolean state;
    private String password;
    private String salt;

    final Digester digester = new Digester(DigestAlgorithm.SHA512);

    private User() {
    }

    public static User create() {

        return new User();
    }

    public void bindUserId(Long userId) {
        this.userId = UserId.create();
        this.userId.setId(userId);
    }

    public String encryption() {
        if (this.salt == null) {
            this.salt = RandomUtil.randomString(64);
        }
        this.password = digester.digestHex(digester.digestHex(this.password + this.salt));
        return this.password;
    }

    public String encryption(String password) {
        if (this.salt == null) {
            this.salt = RandomUtil.randomString(64);
        }
        this.password = digester.digestHex(digester.digestHex(password + this.salt));
        return this.password;
    }

    public Boolean comparePassword(String password) {
        return this.password.equals(this.encryption(password));
    }

    public void setUserId(UserId userId) {
        this.bindUserId(userId.getId());
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


}
