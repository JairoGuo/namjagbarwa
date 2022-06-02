package com.jairoguo.storage.infra.repository.assembler;


import com.jairoguo.storage.domain.model.aggregate.Avatar;
import com.jairoguo.storage.infra.repository.po.AvatarPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Jairo Guo
 */
@Mapper
public interface AvatarAssembler {
    AvatarAssembler INSTANCE = Mappers.getMapper(AvatarAssembler.class);

    @Mapping(target = "hash", source = "hash.hashValue")
    @Mapping(target = "userId", source = "userId.id")
    AvatarPO toAvatarPO(Avatar avatar);

}
