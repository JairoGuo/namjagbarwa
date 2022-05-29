package com.jairoguo.account.interfaces.rest.assembler;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.interfaces.rest.assembler.factory.AccountFactory;
import com.jairoguo.account.interfaces.rest.dto.SignInByPasswordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Jairo Guo
 */
@Mapper(uses = {AccountFactory.class})
public interface SignInAssembler {
    SignInAssembler INSTANCE = Mappers.getMapper(SignInAssembler.class);

    @Mapping(target = "openCode.openCode", source = "signInName")
    @Mapping(target = "user.password", source = "password")
    Account toAccount(SignInByPasswordDTO signInByPasswordDTO);


}
