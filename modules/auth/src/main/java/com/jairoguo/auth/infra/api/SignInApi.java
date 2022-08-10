package com.jairoguo.auth.infra.api;

import com.jairoguo.account.dto.SignInByPasswordDTO;
import com.jairoguo.account.vo.AccountVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jairo Guo
 */

@FeignClient(name = "account-server")
public interface SignInApi {

    @PostMapping("signIn/password")
    AccountVO signInByPassword(@RequestBody SignInByPasswordDTO signInByPasswordDTO);
}
