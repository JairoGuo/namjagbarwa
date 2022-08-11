package com.jairoguo.goods.domain.model.aggregate;



import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.goods.domain.model.entity.Category;
import com.jairoguo.goods.domain.model.entity.Specs;
import com.jairoguo.goods.domain.model.entity.id.GoodsNumber;
import com.jairoguo.goods.domain.model.entity.id.ShippingTemplateNumber;
import com.jairoguo.goods.domain.model.value.Attribute;
import com.jairoguo.goods.domain.model.value.Detail;
import com.jairoguo.goods.domain.model.value.Mark;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品聚合根
 *
 * @author Jairo Guo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Goods implements AggregateRoot<GoodsNumber> {
    /**
     * 商品编号
     */
    private GoodsNumber goodsNumber;
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
    private String[] images;
    /**
     * 店铺id
     */
    private Long storeId;
    /**
     * 关键字
     */
    private String[] keywords;
    /**
     * 店内排序权重
     */
    private Integer sortWeight;
    /**
     * 获得积分
     */
    private BigDecimal giveIntegral;
    /**
     * 浏览量
     */
    private Long visits;
    /**
     * 商品属性
     */
    private List<Attribute> attributeList;
    /**
     * 商品分类
     */
    private Category category;
    /**
     * 商品详情
     */
    private Detail detail;
    /**
     * 商品标记
     */
    private Mark mark;
    /**
     * 商品规格
     */
    private Specs specs;
    /**
     * 邮费模版
     */
    private ShippingTemplateNumber shippingTemplate;

    public static Goods newInstance() {
        return new Goods();
    }

    public static Goods create() {
        Goods goods = new Goods();
        goods.goodsNumber = GoodsNumber.create();
        return goods;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void bindSpecs(Specs specs) {
        this.specs = specs;
    }
}
