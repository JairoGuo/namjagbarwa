package com.jairoguo.account.infra.repository.assembler;

import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.infra.repository.po.AccountPO;
import com.jairoguo.account.infra.repository.po.UserPO;
import com.jairoguo.account.interfaces.rest.assembler.factory.AccountFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Jairo Guo
 */
@Mapper(uses = {AccountFactory.class})
public interface AccountRepositoryAssembler {
    AccountRepositoryAssembler INSTANCE = Mappers.getMapper(AccountRepositoryAssembler.class);

    @Mapping(target = "openCode.openCode", source = "account.openCode")
    @Mapping(target = "openCode.type", source = "account.type")
    @Mapping(target = "user.userId.id", source = "user.id")
    @Mapping(target = "user.password", source = "user.password")
    @Mapping(target = "user.state", source = "user.state")
    @Mapping(target = "user.salt", source = "user.salt")
    Account toAccount(AccountPO account, UserPO user);
    @Mapping(target = "openCode", source = "openCode.openCode")
    @Mapping(target = "type", source = "openCode.type")
    @Mapping(target = "userId", source = "user.userId.id")
    AccountPO toAccountPO(Account account);
    @Mapping(target = ".", source = "user")
    UserPO toUserPO(Account account);

}
