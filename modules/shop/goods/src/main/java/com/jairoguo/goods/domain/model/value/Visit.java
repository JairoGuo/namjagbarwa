package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

/**
 * 此商品被浏览的信息
 *
 * @author Jairo Guo
 */
@Getter
@ToString
public class Visit implements ValueObject {

    private String userId;
    private String ip;
    private String address;
    private String describe;
}
