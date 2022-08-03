package com.jairoguo.seckill.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jairoguo.seckill.infra.repository.po.SeckillGoodsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jairo Guo
 */
@Mapper
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoodsPO> {
}
