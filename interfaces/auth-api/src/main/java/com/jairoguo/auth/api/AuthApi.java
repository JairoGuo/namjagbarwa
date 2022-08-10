package com.jairoguo.auth.api;

import com.jairoguo.auth.dto.UserRoleDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Jairo Guo
 */

// @FeignClient(name = "auth-server")
public interface AuthApi {

    @GetMapping("/login/status")
    Boolean isLogin();

    @GetMapping("/login/checkLogIn")
    Boolean checkLogIn();

    @GetMapping("/login/userId")
    Long getUserId();

    @PostMapping("/role/bind")
    void bindUserRole(@RequestBody UserRoleDTO userRoleDTO);

    @PostMapping("/role/list/{userId}")
    List<String> getRoleList(@PathVariable("userId") Long userId);
}
