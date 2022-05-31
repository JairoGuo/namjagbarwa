package com.jairoguo.account.interfaces.rest.assembler;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.interfaces.rest.assembler.factory.AccountFactory;
import com.jairoguo.account.interfaces.rest.dto.SignUpByEmailDTO;
import com.jairoguo.account.interfaces.rest.dto.SignUpByPhoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Jairo Guo
 */
@Mapper(uses = {AccountFactory.class})
public interface SignUpAssembler {
    SignUpAssembler INSTANCE = Mappers.getMapper(SignUpAssembler.class);

    @Mapping(target = "openCode.openCode", source = "email")
    @Mapping(target = "user.password", source = "password")
    Account toAccount(SignUpByEmailDTO signUpByEmail);
    @Mapping(target = "openCode.openCode", source = "phone")
    @Mapping(target = "user.password", source = "password")
    Account toAccount(SignUpByPhoneDTO signUpByPhoneDTO);


}
