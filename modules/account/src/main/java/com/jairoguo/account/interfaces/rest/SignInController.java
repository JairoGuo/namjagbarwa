package com.jairoguo.account.interfaces.rest;

import com.jairoguo.account.application.bo.SignInBO;
import com.jairoguo.account.application.service.SignInApplicationService;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.interfaces.rest.assembler.SignInAssembler;
import com.jairoguo.account.interfaces.rest.dto.SignInByPasswordDTO;
import com.jairoguo.account.interfaces.rest.dto.SignInBySmsCodeDTO;
import com.jairoguo.account.interfaces.rest.vo.AccountVO;
import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
        SignInBO signInBO = SignInBO.builder().account(account).build();
        Account loginAccount = signInApplicationService.login(signInBO);
        AccountVO accountVo = new AccountVO(loginAccount.getUser().getUserId().getId(), "");

        return Result.success(accountVo);
    }

    @PostMapping("smsCode")
    public ResultBody<AccountVO> signInBySmsCode(@RequestBody SignInBySmsCodeDTO signInBySmsCodeDTO) {


        Account account = SignInAssembler.INSTANCE.toAccount(signInBySmsCodeDTO);
        SignInBO signInBO = SignInBO.builder().account(account).code(signInBySmsCodeDTO.code()).build();
        account = signInApplicationService.login(signInBO);
        AccountVO accountVo = new AccountVO(account.getUser().getUserId().getId(), "");

        return Result.success(accountVo);
    }
}
