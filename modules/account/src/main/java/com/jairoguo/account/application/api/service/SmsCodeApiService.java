package com.jairoguo.account.application.api.service;

import com.jairoguo.account.application.api.SmsCodeApi;
import com.jairoguo.account.application.api.dto.VerifyCodeDTO;
import com.jairoguo.account.application.api.vo.VerifyCode;
import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class SmsCodeApiService {
    @Resource
    private SmsCodeApi smsCodeApi;

    public VerifyCode verifySmsCode(VerifyCodeDTO verifyCodeDTO) {

        ResultBody<VerifyCode> verifyCodeResultBody = smsCodeApi.verifySmsCode(verifyCodeDTO);

        if (Boolean.FALSE.equals(verifyCodeResultBody.getSuccess())) {
            Result.fail(verifyCodeResultBody.getMsg());
        }
        return verifyCodeResultBody.getData();

    }
}
