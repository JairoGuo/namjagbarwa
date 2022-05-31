package com.jairoguo.sms.domain.repository;

import com.jairoguo.common.base.Repository;
import com.jairoguo.sms.domain.model.aggregate.PhoneCode;
import com.jairoguo.sms.domain.model.entity.id.Phone;

import java.util.concurrent.TimeUnit;

public interface CodeCacheRepository extends Repository<PhoneCode, Phone> {

    void save(PhoneCode phoneCode, Long time, TimeUnit timeUnit);

    String getCode(PhoneCode phoneCode);

    void delete(PhoneCode phoneCode);
}
