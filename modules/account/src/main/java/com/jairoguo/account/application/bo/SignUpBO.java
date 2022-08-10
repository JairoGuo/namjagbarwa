package com.jairoguo.account.application.bo;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.auth.dto.RoleTypeEnum;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Jairo Guo
 */
@Builder
@Getter
public class SignUpBO {
    private Account account;
    private String smsCode;
    private RoleTypeEnum roleType;

}
