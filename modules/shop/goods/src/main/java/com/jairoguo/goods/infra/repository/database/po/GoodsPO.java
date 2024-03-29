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
 * @author Jairo Guo
 */
@Getter
@Setter
@Entity
@TableName("t_goods")
@Table(name = "t_goods")
public class GoodsPO extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品编号
     */
    private Long goodsNumber;
    /**
     * 商品详情id
     */
    private Long goodsDetailId;
    /**
     * 商品属性id
     */
    private Long attributeId;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 产品简介
     */
    private String introduction;
    /**
     * 商品封面
     */
    private String cover;
    /**
     * 商品图片
     */
    private String images;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 店铺id
     */
    private Long storeId;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 店内排序权重
     */
    private Integer sortWeight;

    /**
     * 获得积分
     */
    private BigDecimal giveIntegral;
    /**
     * 邮费
     */
    private BigDecimal postage;
    /**
     * 状态（false：未上架，true：上架）
     */
    private Boolean isShow;
    /**
     * 是否热卖
     */
    private Boolean isHot;
    /**
     * 是否优惠
     */
    private Boolean isBenefit;
    /**
     * 是否新品
     */
    private Boolean isNew;
    /**
     * 是否包邮
     */
    private Boolean isPostage;
    /**
     * 浏览量
     */
    private Long visits;
}
