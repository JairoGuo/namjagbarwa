package com.jairoguo.auth.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jairoguo.auth.infra.repository.po.RolePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jairo Guo
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePO> {
}
