package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

/**
 * 配送区域
 *
 * @author Jairo Guo
 */
@Getter
@ToString
public class DeliveryArea implements ValueObject {
    /**
     * 配送区域
     */
    private String area;
    /**
     * 运费
     */
    private String postage;
    /**
     * 是否包邮
     */
    private Boolean isPostage;
}
