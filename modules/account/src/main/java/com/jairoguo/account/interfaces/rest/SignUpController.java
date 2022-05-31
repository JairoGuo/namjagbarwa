package com.jairoguo.account.interfaces.rest;

import com.jairoguo.account.application.bo.SignUpBO;
import com.jairoguo.account.application.service.SignUpApplicationService;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.interfaces.rest.assembler.SignUpAssembler;
import com.jairoguo.account.interfaces.rest.dto.SignUpByEmailDTO;
import com.jairoguo.account.interfaces.rest.dto.SignUpByPhoneDTO;
import com.jairoguo.account.interfaces.rest.vo.AccountVO;
import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Jairo Guo
 */
@Validated
@RestController
@RequestMapping("signUp")
public class SignUpController {

    @Resource
    private SignUpApplicationService signUpApplicationService;

    /**
     * 通过邮件注册.
     *
     */
    @PostMapping("email")
    public ResultBody<AccountVO> signUpByEmail(@RequestBody @Valid SignUpByEmailDTO signUpByEmailDTO) {
        Account account = SignUpAssembler.INSTANCE.toAccount(signUpByEmailDTO);
        SignUpBO signUpBO = SignUpBO.builder().account(account).build();
        account = signUpApplicationService.register(signUpBO);
        AccountVO accountVo = new AccountVO(account.getUser().getUserId().getId(), "");

        return Result.success(accountVo);
    }

    /**
     * 通过手机号注册.
     */
    @PostMapping("phone")
    public ResultBody<AccountVO> signUpByPhone(@RequestBody SignUpByPhoneDTO signUpByPhoneDTO) {

        Account account = SignUpAssembler.INSTANCE.toAccount(signUpByPhoneDTO);
        SignUpBO signUpBO = SignUpBO.builder()
                .account(account)
                .smsCode(signUpByPhoneDTO.code())
                .build();
        account = signUpApplicationService.register(signUpBO);
        AccountVO accountVo = new AccountVO(account.getUser().getUserId().getId(), "");

        return Result.success(accountVo);
    }
}
