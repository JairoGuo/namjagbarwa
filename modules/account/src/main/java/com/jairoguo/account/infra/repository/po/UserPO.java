package com.jairoguo.account.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jairo Guo
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_user")
@TableName("t_user")
public class UserPO extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private boolean state;

    @Column(length = 64)
    private String salt;

    @Column(length = 128)
    private String password;

}

