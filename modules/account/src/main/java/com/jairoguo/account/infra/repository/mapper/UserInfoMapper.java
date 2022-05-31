package com.jairoguo.account.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jairoguo.account.infra.repository.po.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jairo Guo
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoPO> {
}
