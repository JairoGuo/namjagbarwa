package com.jairoguo.auth.dto;

/**
 * @author Jairo Guo
 */
public record UserRoleDTO(
        Long userId,
        RoleTypeEnum roleTyp
) {
}
