package com.jairoguo.sms.domain.service;

import com.jairoguo.common.base.DomainService;
import com.jairoguo.sms.domain.model.aggregate.PhoneCode;

public interface CodeDomainService extends DomainService {
    String sendCode(PhoneCode phoneCode);

    Boolean verifyCode(PhoneCode phoneCode);
}
