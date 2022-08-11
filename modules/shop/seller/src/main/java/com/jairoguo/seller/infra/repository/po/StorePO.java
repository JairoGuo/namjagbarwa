package com.jairoguo.seller.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jairo Guo
 */
@Getter
@Setter
@Entity
@TableName("t_store")
@Table(name = "t_store")
public class StorePO extends BaseEntity {
    private Long userId;
    private String name;
    private String introduce;
}
