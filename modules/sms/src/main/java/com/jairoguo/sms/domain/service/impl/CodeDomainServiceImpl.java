package com.jairoguo.sms.domain.service.impl;

import com.jairoguo.common.result.Result;
import com.jairoguo.sms.domain.model.aggregate.PhoneCode;
import com.jairoguo.sms.domain.repository.CodeCacheRepository;
import com.jairoguo.sms.domain.service.CodeDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Jairo Guo
 */
@Service
public class CodeDomainServiceImpl implements CodeDomainService {

    @Resource
    private CodeCacheRepository codeCacheRepository;

    @Override
    public String sendCode(PhoneCode phoneCode) {
        phoneCode.generateCode();
        codeCacheRepository.save(phoneCode, 3L, TimeUnit.MINUTES);
        return phoneCode.getCode().getCode();
    }

    @Override
    public Boolean verifyCode(PhoneCode phoneCode) {
        String code = codeCacheRepository.getCode(phoneCode);

        if (code != null) {

            if (code.equals(phoneCode.getCode().getCode())) {
                codeCacheRepository.delete(phoneCode.getPhone());
            } else {
                Result.fail("验证码不正确");
            }
        } else {
            Result.fail("验证码过期或未发送验证码");

        }
        return true;

    }
}