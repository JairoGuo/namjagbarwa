package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

/**
 * 商品详细信息
 *
 * @author Jairo Guo
 */
@Getter
@ToString
public class Detail implements ValueObject {

    private String content;

    /**
     * 产品条码（一维码）
     */
    private String barCode;
}
