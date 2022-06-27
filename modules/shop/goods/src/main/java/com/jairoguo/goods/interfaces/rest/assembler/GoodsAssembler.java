package com.jairoguo.goods.interfaces.rest.assembler;


import com.jairoguo.goods.domain.model.aggregate.Goods;

import com.jairoguo.goods.infra.factory.GoodsFactory;
import com.jairoguo.goods.interfaces.rest.dto.GoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Jairo Guo
 */
@Mapper(uses = {GoodsFactory.class})
public interface GoodsAssembler {
    GoodsAssembler INSTANCE = Mappers.getMapper(GoodsAssembler.class);

    @Mapping(target = "price", ignore = true)
    Goods toProduct(GoodsDTO goodsDTO);


}
