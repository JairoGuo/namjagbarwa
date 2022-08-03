package com.jairoguo.seckill.domain.model.aggregate;

import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.common.result.Result;
import com.jairoguo.seckill.domain.model.entity.id.SeckillGoodsNumber;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jairo Guo
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeckillGoods implements AggregateRoot<SeckillGoodsNumber> {

    private Long goodsId;
    private Long specsAttributeId;
    private Long stock;

    private BigDecimal price;
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static SeckillGoods create() {
        return new SeckillGoods();
    }

    public void bindDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            Result.fail("秒杀时间错误");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

}
