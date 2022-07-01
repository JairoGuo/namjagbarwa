package com.jairoguo.goods.domain.model.entity;


import com.jairoguo.common.base.Entity;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import lombok.*;

/**
 * 商品分类
 *
 * @author Jairo Guo
 */
@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Category implements Entity<GoodsNumber> {

    /**
     * 父id
     */
    private Long parentId;
    private Long categoryId;

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

    public static Category create() {
        return new Category();
    }

}
