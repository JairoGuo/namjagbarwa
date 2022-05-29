package com.jairoguo.account.interfaces.rest;

import com.jairoguo.account.application.bo.SignInBO;
import com.jairoguo.account.application.service.SignInApplicationService;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.interfaces.rest.assembler.SignInAssembler;
import com.jairoguo.account.interfaces.rest.dto.SignInByPasswordDTO;
import com.jairoguo.account.interfaces.rest.vo.AccountVO;
import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("signIn")
public class SignInController {

    @Resource
    private SignInApplicationService signInApplicationService;

    @PostMapping("password")
    public ResultBody<AccountVO> signInByPassword(@RequestBody SignInByPasswordDTO signInByPasswordDTO) {
        Account account = SignInAssembler.INSTANCE.toAccount(signInByPasswordDTO);
        SignInBO signInBO = new SignInBO(account);
        Account loginAccount = signInApplicationService.login(signInBO);
        AccountVO accountVo = new AccountVO(loginAccount.getUser().getUserId().getId(), "");

        return Result.success(accountVo);
    }
}
