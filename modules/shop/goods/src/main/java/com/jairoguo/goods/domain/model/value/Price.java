package com.jairoguo.goods.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 商品价格
 *
 * @author Jairo Guo
 */

@Getter
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

    private Price() {

    }

    public static Price create() {

        return new Price();
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public void setStudentPrice(BigDecimal studentPrice) {
        this.studentPrice = studentPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

}
