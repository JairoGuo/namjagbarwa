package com.jairoguo.account.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Jairo Guo
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_userinfo")
@TableName("t_userinfo")
public class UserInfoPO extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    @Column(length = 20)
    private String username;
    @Column(length = 20)
    private String personalizedUrl;
    private LocalDate birthday;
    @Column(length = 10)
    private String sex;
    private String website;
    private String avatar;
}
