package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 商品价格
 *
 * @author Jairo Guo
 */

@Data
@ToString
public class Price implements ValueObject {
    /**
     * 商品出售价格
     */
    private BigDecimal sellPrice;
    /**
     * 成本价
     */
    private BigDecimal cost;
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


}
