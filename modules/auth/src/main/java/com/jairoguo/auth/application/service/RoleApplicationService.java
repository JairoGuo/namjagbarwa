package com.jairoguo.auth.application.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jairoguo.auth.dto.RoleTypeEnum;
import com.jairoguo.auth.infra.repository.mapper.RoleMapper;
import com.jairoguo.auth.infra.repository.mapper.UserRoleMapper;
import com.jairoguo.auth.infra.repository.po.RolePO;
import com.jairoguo.auth.infra.repository.po.UserRolePO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Jairo Guo
 * @date 2022/2/16 15:40
 */
@Service
public class RoleApplicationService {

    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;

    public Object addRole() {

        return null;
    }

    public void bindUserRole(Long userId, RoleTypeEnum roleType) {

        UserRolePO userRolePO = new UserRolePO();
        userRolePO.setUserId(userId);
        QueryWrapper<RolePO> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.lambda()
                        .eq(RolePO::getName, roleType);
        RolePO rolePO = roleMapper.selectOne(roleQueryWrapper);
        userRolePO.setRoleId(rolePO.getId());
        userRoleMapper.insert(userRolePO);
    }

    public List<String> getUserRoleList(Long userId) {
        QueryWrapper<UserRolePO> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda()
                .eq(UserRolePO::getUserId, userId);
        List<UserRolePO> userRoleList = userRoleMapper.selectList(userRoleQueryWrapper);
        System.out.println(userRoleList);
        if (userRoleList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> roleIdList = userRoleList.stream().map(UserRolePO::getRoleId).toList();

        QueryWrapper<RolePO> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.lambda()
                .in(RolePO::getId, roleIdList);
        List<RolePO> roleList = roleMapper.selectList(roleQueryWrapper);
        return roleList.stream().map(RolePO::getName).toList();
    }
}
