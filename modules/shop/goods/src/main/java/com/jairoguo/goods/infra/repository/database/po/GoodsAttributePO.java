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
@TableName("t_goods_attr")
@Table(name = "t_goods_attr")
public class GoodsAttributePO extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 属性名
     */
    private String attributeName;

    /**
     * 属性值
     */
    private String attributeValues;

}
