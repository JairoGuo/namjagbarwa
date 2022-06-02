package com.jairoguo.account.application.service;

import com.jairoguo.account.application.api.dto.VerifyCodeDTO;
import com.jairoguo.account.application.api.service.SmsCodeApiService;
import com.jairoguo.account.application.bo.SignUpBO;
import com.jairoguo.account.application.event.publish.AvatarPublish;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.service.AccountDomainService;
import com.jairoguo.common.base.ApplicationService;
import com.jairoguo.common.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Jairo Guo
 */
@Service
public class SignUpApplicationService implements ApplicationService {

    @Resource
    private AccountDomainService accountDomainService;

    @Resource
    private SmsCodeApiService smsCodeApiService;

    @Resource
    private AvatarPublish avatarPublish;

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
                smsCodeApiService.verifySmsCode(verifyCodeDTO);
                signUpBO.getAccount().getUser().setState(true);
                account = accountDomainService.createAccount(signUpBO.getAccount());

            }
            default -> Result.fail("不可知的注册类型");
        }

        if (Optional.ofNullable(account).isPresent()) {
            avatarPublish.initAvatar(account.getUser().getUserId().getId());

        }



        return account;
    }

}
