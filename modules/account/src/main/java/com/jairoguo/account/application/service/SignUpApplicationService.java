package com.jairoguo.account.application.service;

import com.jairoguo.account.application.api.SmsCodeApi;
import com.jairoguo.account.application.api.dto.VerifyCodeDTO;
import com.jairoguo.account.application.api.vo.VerifyCodeVO;
import com.jairoguo.account.application.bo.SignUpBO;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.service.AccountDomainService;
import com.jairoguo.common.base.ApplicationService;
import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class SignUpApplicationService implements ApplicationService {

    @Resource
    private AccountDomainService accountDomainService;

    @Resource
    private SmsCodeApi smsCodeApi;

    @Transactional(rollbackFor = Exception.class)
    public Account register(SignUpBO signUpBO) {

        Account account = null;
        switch (signUpBO.getAccount().getOpenCode().getType()) {

            case EMAIL -> {
                account = accountDomainService.createAccount(signUpBO.getAccount());
                // TODO: 发送验证邮件

            }
            case PHONE -> {
                VerifyCodeDTO verifyCodeDTO =
                        new VerifyCodeDTO(signUpBO.getAccount().getOpenCode().getOpenCode(), "REGISTER", signUpBO.getSmsCode());
                ResultBody<VerifyCodeVO> verifyCodeVO = smsCodeApi.verifySmsCode(verifyCodeDTO);
                if (Boolean.FALSE.equals(verifyCodeVO.getSuccess()) || Boolean.FALSE.equals(verifyCodeVO.getData().status())) {
                    Result.fail(verifyCodeVO.getMsg());
                }

                account = accountDomainService.createAccount(signUpBO.getAccount());

            }
            default -> {}
        }


        return account;
    }

}
