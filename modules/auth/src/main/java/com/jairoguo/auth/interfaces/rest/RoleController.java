package com.jairoguo.auth.interfaces.rest;

import com.jairoguo.auth.application.service.RoleApplicationService;
import com.jairoguo.auth.dto.UserRoleDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleApplicationService roleApplicationService;

    @PostMapping("bind")
    public void bindUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        roleApplicationService.bindUserRole(userRoleDTO.userId(), userRoleDTO.roleTyp());
    }

    @PostMapping("list/{userId}")
    public List<String> getRoleList(@PathVariable Long userId) {
        return roleApplicationService.getUserRoleList(userId);
    }
}
