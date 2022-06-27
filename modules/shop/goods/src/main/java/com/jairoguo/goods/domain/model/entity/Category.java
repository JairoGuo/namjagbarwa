package com.jairoguo.goods.domain.model.entity;


import com.jairoguo.common.base.Entity;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品分类
 *
 * @author Jairo Guo
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Category implements Entity<GoodsNumber> {

    /**
     * 父id
     */
    private Integer goodsId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否推荐
     */
    private Boolean isRecommend;

}
