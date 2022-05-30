package com.jairoguo.sms.application.service;

import com.jairoguo.sms.domain.model.aggregate.PhoneCode;
import com.jairoguo.sms.domain.service.CodeDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class CodeApplicationService {
    @Resource
    private CodeDomainService codeDomainService;
    public Boolean verifyCode(PhoneCode phoneCode) {
        return codeDomainService.verifyCode(phoneCode);
    }

    public String getCode(PhoneCode phoneCode) {
        return codeDomainService.sendCode(phoneCode);
    }
}
