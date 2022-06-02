package com.jairoguo.storage.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jairoguo.storage.domain.model.aggregate.Avatar;
import com.jairoguo.storage.domain.model.entity.id.UserId;
import com.jairoguo.storage.domain.repository.AvatarRepository;
import com.jairoguo.storage.infra.repository.assembler.AvatarAssembler;
import com.jairoguo.storage.infra.repository.factory.AvatarFactory;
import com.jairoguo.storage.infra.repository.mapper.AvatarMapper;
import com.jairoguo.storage.infra.repository.po.AvatarPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Repository
public class AvatarRepositoryImpl implements AvatarRepository {


    @Resource
    private AvatarMapper avatarMapper;

    @Override
    public Boolean save(Avatar aggregate) {

        AvatarPO avatarPO = AvatarAssembler.INSTANCE.toAvatarPO(aggregate);

        return avatarMapper.insert(avatarPO) == 1;
    }

    @Override
    public Avatar findById(UserId id) {
        QueryWrapper<AvatarPO> avatarQueryWrapper = new QueryWrapper<>();
        avatarQueryWrapper.lambda().eq(AvatarPO::getUserId, id.getId());
        AvatarPO avatarPO = avatarMapper.selectOne(avatarQueryWrapper);
        return AvatarFactory.toAvatar(avatarPO);
    }

    @Override
    public Boolean delete(UserId id) {
        return null;
    }

    @Override
    public Boolean update(Avatar aggregate) {
        AvatarPO avatarPO = AvatarAssembler.INSTANCE.toAvatarPO(aggregate);
        UpdateWrapper<AvatarPO> avatarPOUpdateWrapper = new UpdateWrapper<>();
        avatarPOUpdateWrapper.lambda().eq(AvatarPO::getUserId, aggregate.getUserId().getId());
        return avatarMapper.update(avatarPO, avatarPOUpdateWrapper) == 1;
    }
}
