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
@Table(name = "t_role")
@TableName("t_role")
public class RolePO extends BaseEntity {

    private Integer parentId;
    private String code;
    private String name;
    private String intro;
}
