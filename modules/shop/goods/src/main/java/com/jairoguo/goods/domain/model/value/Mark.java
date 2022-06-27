package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

/**
 * 商品标记属性
 *
 * @author Jairo Guo
 */
@Getter
@ToString
public class Mark implements ValueObject {
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
     * 是否新品
     */
    private Boolean isRecommend;
    /**
     * 是否包邮
     */
    private Boolean isPostage;
}
