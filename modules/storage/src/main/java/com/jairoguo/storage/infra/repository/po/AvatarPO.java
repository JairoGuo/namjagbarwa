package com.jairoguo.storage.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jairo Guo
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_avatar")
@TableName("t_avatar")
public class AvatarPO extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String hash;
    private String objectKey;
}
