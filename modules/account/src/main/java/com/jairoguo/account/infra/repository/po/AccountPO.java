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
@Table(name = "t_account")
@TableName("t_account")
public class AccountPO extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(length = 20)
    private Long userId;

    @Column(length = 50)
    private String openCode;

    @Column
    private String type;

}
