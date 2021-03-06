package com.jairoguo.account.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jairoguo.account.domain.model.aggregate.Account;
import com.jairoguo.account.domain.model.entity.UserInfo;
import com.jairoguo.account.domain.model.entity.id.OpenCode;
import com.jairoguo.account.domain.model.entity.id.UserId;
import com.jairoguo.account.domain.repository.AccountRepository;
import com.jairoguo.account.infra.repository.assembler.AccountRepositoryAssembler;
import com.jairoguo.account.infra.repository.mapper.AccountMapper;
import com.jairoguo.account.infra.repository.mapper.UserInfoMapper;
import com.jairoguo.account.infra.repository.mapper.UserMapper;
import com.jairoguo.account.infra.repository.po.AccountPO;
import com.jairoguo.account.infra.repository.po.UserInfoPO;
import com.jairoguo.account.infra.repository.po.UserPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 * @author Jairo Guo
 */

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean save(Account aggregate) {
        UserPO userPO = null;
        UserInfoPO userInfoPO = null;

        AccountPO accountPO = AccountRepositoryAssembler.INSTANCE.toAccountPO(aggregate);
        if (aggregate.getUser().getUserId() == null) {
            userPO = AccountRepositoryAssembler.INSTANCE.toUserPO(aggregate);
            userMapper.insert(userPO);
            userInfoPO = new UserInfoPO();
            userInfoPO.setUserId(userPO.getId());
            userInfoMapper.insert(userInfoPO);
            accountPO.setUserId(userPO.getId());
            aggregate.getUser().bindUserId(userPO.getId());


            UserInfo userInfo = UserInfo.create();
            UserId userId = UserId.create();
            userId.setId(userPO.getId());
            userInfo.setUserId(userId);
            aggregate.bindUserInfo(userInfo);
        }

        return accountMapper.insert(accountPO) == 1;
    }

    @Override
    public Account findById(OpenCode id) {
        QueryWrapper<AccountPO> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.lambda().eq(AccountPO::getOpenCode, id.getOpenCode());
        AccountPO accountPO = accountMapper.selectOne(accountQueryWrapper);
        if (accountPO == null) {
            return null;
        }
        UserPO userPO = userMapper.selectById(accountPO.getUserId());

        return AccountRepositoryAssembler.INSTANCE.toAccount(accountPO, userPO);
    }

    @Override
    public Boolean delete(OpenCode id) {
        return null;
    }

    @Override
    public Boolean update(Account aggregate) {
        return null;
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {

        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setAvatar(userInfo.getAvatar());
        UpdateWrapper<UserInfoPO> userInfoPOUpdateWrapper = new UpdateWrapper<>();
        userInfoPOUpdateWrapper.lambda().eq(UserInfoPO::getUserId, userInfo.getUserId().getId());
        return userInfoMapper.update(userInfoPO, userInfoPOUpdateWrapper) == 1;
    }
}
