package com.jairoguo.goods.vo;


import java.math.BigDecimal;

/**
 * 商品价格
 *
 * @author Jairo Guo
 */


public record PriceVO(
        /**
         * 商品出售价格
         */
        BigDecimal sellPrice,
        /**
         * 成本价
         */
        BigDecimal cost,
        /**
         * 会员价格
         */
        BigDecimal vipPrice,
        /**
         * 学生价格
         */
        BigDecimal studentPrice,
        /**
         * 税价
         */
        BigDecimal taxPrice
) {


}
