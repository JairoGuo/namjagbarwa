package com.jairoguo.order.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jairoguo.order.infra.repository.po.OrderPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jairo Guo
 */

@Mapper
public interface OrderMapper extends BaseMapper<OrderPO> {
}
