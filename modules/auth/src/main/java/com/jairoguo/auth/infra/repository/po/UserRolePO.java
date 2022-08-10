package com.jairoguo.auth.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jairo Guo
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_user_role")
@TableName("t_user_role")
public class UserRolePO extends BaseEntity {
    private Long userId;
    private Long roleId;
}
