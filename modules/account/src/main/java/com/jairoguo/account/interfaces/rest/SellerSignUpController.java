package com.jairoguo.account.interfaces.rest;

import com.jairoguo.account.application.bo.SignUpBO;
import com.jairoguo.account.application.service.SignUpApplicationService;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.model.entity.User;
import com.jairoguo.account.domain.model.entity.id.OpenCode;
import com.jairoguo.account.interfaces.rest.dto.SellerSignUpDTO;
import com.jairoguo.account.interfaces.rest.vo.AccountVO;
import com.jairoguo.auth.dto.RoleTypeEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("seller")
public class SellerSignUpController {

    @Resource
    private SignUpApplicationService signUpApplicationService;

    @PostMapping("signUp")
    public AccountVO signUp(@RequestBody SellerSignUpDTO sellerSignUpDTO) {

        Account account = Account.create();
        OpenCode openCode = OpenCode.create();
        openCode.setOpenCode(sellerSignUpDTO.phone());
        account.setOpenCode(openCode);
        User user = User.create();
        user.setPassword(sellerSignUpDTO.password());
        Account registerAccount = signUpApplicationService.register(SignUpBO.builder()
                .account(account)
                .smsCode(sellerSignUpDTO.smsCode())
                .roleType(RoleTypeEnum.SELLER)
                .build());

        return new AccountVO(registerAccount.getUser().getUserId().getId(), "");
    }
}
