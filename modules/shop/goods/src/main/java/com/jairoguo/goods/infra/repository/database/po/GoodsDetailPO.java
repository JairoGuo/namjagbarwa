package com.jairoguo.goods.infra.repository.database.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;

/**
 * @author Jairo Guo
 */
@Getter
@Setter
@Entity
@TableName("t_goods_detail")
@Table(name = "t_goods_detail")
public class GoodsDetailPO extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private String content;

    /**
     * 产品条码（一维码）
     */
    private String barCode;
}
