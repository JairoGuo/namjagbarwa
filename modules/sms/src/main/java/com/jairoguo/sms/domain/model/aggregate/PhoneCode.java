package com.jairoguo.sms.domain.model.aggregate;

import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.sms.domain.model.entity.Code;
import com.jairoguo.sms.domain.model.entity.id.Phone;
import com.jairoguo.sms.domain.model.value.CodeUseEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PhoneCode implements AggregateRoot<Phone> {
    private Phone phone;
    private Code code;

    private CodeUseEnum use;

    private PhoneCode() {
    }

    public static PhoneCode create() {
        return new PhoneCode();
    }


    public void bindPhone(String phone) {
        Phone phoneNumber = Phone.create();
        phoneNumber.setPhone(phone);
        this.phone = phoneNumber;

    }

    public void generateCode() {
        Code code = Code.create();
        code.generateCode();
        this.code = code;
    }

    public void setCode(String code) {
        Code code1 = Code.create();
        code1.setCode(code);
        this.code = code1;
    }

    public void bindUse(CodeUseEnum use) {
        this.use = use;
    }
}
