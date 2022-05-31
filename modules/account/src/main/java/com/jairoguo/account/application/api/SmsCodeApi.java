package com.jairoguo.account.application.api;

import com.jairoguo.account.application.api.dto.VerifyCodeDTO;
import com.jairoguo.account.application.api.vo.VerifyCode;
import com.jairoguo.common.result.ResultBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author Jairo Guo
 */
@FeignClient(name = "sms", path = "code")
public interface SmsCodeApi {

    @PostMapping("verify")
    ResultBody<VerifyCode> verifySmsCode(VerifyCodeDTO verifyCodeDTO);
}
