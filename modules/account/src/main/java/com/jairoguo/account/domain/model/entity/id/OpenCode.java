package com.jairoguo.account.domain.model.entity.id;

import cn.hutool.core.lang.Validator;
import com.jairoguo.account.domain.model.value.OpenCodeTypeEnum;
import com.jairoguo.common.base.Id;
import lombok.Getter;

/**
 * @author Jairo Guo
 */
@Getter
public class OpenCode implements Id {
    private String openCode;
    private OpenCodeTypeEnum type;

    private OpenCode() {
    }

    public static OpenCode create() {
        return new OpenCode();
    }
    public void setOpenCode(String openCode) {
        if (Validator.isMobile(openCode)) {
            this.type = OpenCodeTypeEnum.PHONE;
        } else if (Validator.isEmail(openCode)) {
            this.type = OpenCodeTypeEnum.EMAIL;
        } else if (Validator.isMatchRegex("[a-zA-Z][a-zA-Z0-9]+", openCode)){
            this.type = OpenCodeTypeEnum.USERNAME;
        } else {
            this.type = OpenCodeTypeEnum.AUTHORIZE;
        }
        this.openCode = openCode;

    }

    public void setType(OpenCodeTypeEnum type) {
        this.type = type;
    }
}
