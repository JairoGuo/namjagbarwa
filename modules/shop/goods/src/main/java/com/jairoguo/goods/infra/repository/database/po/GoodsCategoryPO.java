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
@TableName("t_goods_category")
@Table(name = "t_goods_category")
public class GoodsCategoryPO extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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
