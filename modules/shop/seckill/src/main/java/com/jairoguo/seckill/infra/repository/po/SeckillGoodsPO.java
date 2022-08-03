package com.jairoguo.seckill.infra.repository.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jairoguo.database.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jairo Guo
 */

@Getter
@Setter
@Entity
@TableName("t_seckill_goods")
@Table(name = "t_seckill_goods")
public class SeckillGoodsPO extends BaseEntity {
    private Long goodsId;
    private Long specsAttributeId;
    private Long stock;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
