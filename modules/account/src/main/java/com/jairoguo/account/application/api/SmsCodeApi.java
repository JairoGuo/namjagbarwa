package com.jairoguo.account.application.api;

import com.jairoguo.account.application.api.dto.VerifyCodeDTO;
import com.jairoguo.account.application.api.vo.VerifyCodeVO;
import com.jairoguo.common.result.ResultBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author Jairo Guo
 */
@FeignClient(name = "sms", path = "code")
public interface SmsCodeApi {

    @PostMapping("verify")
    ResultBody<VerifyCodeVO> verifySmsCode(@RequestBody VerifyCodeDTO verifyCodeDTO);
}
