package com.jairoguo.auth.interfaces.rest;

import cn.dev33.satoken.stp.StpUtil;
import com.jairoguo.account.dto.SignInByPasswordDTO;
import com.jairoguo.account.vo.AccountVO;
import com.jairoguo.auth.dto.LogInDTO;
import com.jairoguo.auth.infra.api.SignInApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */

@RestController
@RequestMapping("login")
public class LogInController {

    @Resource
    private SignInApi signInApi;


    @PostMapping("login")
    public AccountVO login(@RequestBody LogInDTO logInDTO) {

        SignInByPasswordDTO signInByPasswordDTO = new SignInByPasswordDTO(logInDTO.openCode(), logInDTO.password());
        AccountVO accountVO = signInApi.signInByPassword(signInByPasswordDTO);
        StpUtil.login(accountVO.userId());

        return new AccountVO(accountVO.userId(), StpUtil.getTokenValue());
    }

    @GetMapping("status")
    public Boolean isLogIn() {
        return StpUtil.isLogin();
    }

    @GetMapping("userId")
    public Long getUserId() {
        return StpUtil.getLoginIdAsLong();
    }
}
