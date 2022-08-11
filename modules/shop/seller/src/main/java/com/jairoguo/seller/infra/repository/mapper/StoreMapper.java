package com.jairoguo.seller.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jairoguo.seller.infra.repository.po.StorePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jairo Guo
 */
@Mapper
public interface StoreMapper extends BaseMapper<StorePO> {
}
