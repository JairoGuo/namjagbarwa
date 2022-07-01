package com.jairoguo.goods.infra.repository.database.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.math.BigDecimal;

/**
 * 商品规格属性
 *
 * @author Jairo Guo
 */
@Getter
@Setter
@Entity
@TableName("t_specs_attribute")
@Table(name = "t_specs_attribute")
public class SpecsAttributePO extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long goodsId;

    /**
     * 商品属性索引值 (attr_value|attr_value[|....])
     */
    private String sku;

    /**
     * 属性对应的库存
     */
    private Long stock;

    /**
     * 销量
     */
    private Integer sales;
    /**
     * 商品出售价格
     */
    private BigDecimal sellPrice;
    /**
     * 成本价
     */
    private BigDecimal costPrice;
    /**
     * 会员价格
     */
    private BigDecimal vipPrice;
    /**
     * 学生价格
     */
    private BigDecimal studentPrice;
    /**
     * 税价
     */
    private BigDecimal taxPrice;

    /**
     * 图片
     */
    private String image;

    /**
     * 唯一值
     */
    // private String unique;

}
