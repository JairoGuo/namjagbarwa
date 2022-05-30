package com.jairoguo.sms.interfaces.rest;

import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import com.jairoguo.sms.application.service.CodeApplicationService;
import com.jairoguo.sms.domain.model.aggregate.PhoneCode;
import com.jairoguo.sms.interfaces.rest.dto.CodeDTO;
import com.jairoguo.sms.interfaces.rest.dto.VerifyCodeDTO;
import com.jairoguo.sms.interfaces.rest.vo.CodeVO;
import com.jairoguo.sms.interfaces.rest.vo.VerifyCodeVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("code")
public class CodeController {

    @Resource
    private CodeApplicationService codeApplicationService;

    @PostMapping("get")
    public ResultBody<CodeVO> getSmsCode(@RequestBody @Valid CodeDTO codeDTO) {
        PhoneCode phoneCode = PhoneCode.create();
        phoneCode.bindPhone(codeDTO.phone());
        phoneCode.bindUse(codeDTO.use());
        String code = codeApplicationService.getCode(phoneCode);
        CodeVO codeVO = new CodeVO(code);
        return Result.success(codeVO);
    }

    @PostMapping("verify")
    public ResultBody<VerifyCodeVO> verifySmsCode(@RequestBody VerifyCodeDTO verifyCodeDTO) {
        PhoneCode phoneCode = PhoneCode.create();
        phoneCode.bindPhone(verifyCodeDTO.phone());
        phoneCode.bindUse(verifyCodeDTO.use());
        phoneCode.setCode(verifyCodeDTO.code());
        Boolean status = codeApplicationService.verifyCode(phoneCode);
        return Result.success(new VerifyCodeVO(verifyCodeDTO.phone(), status));
    }
}
