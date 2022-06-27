package com.jairoguo.goods.interfaces.rest.assembler;



import com.jairoguo.goods.domain.model.value.Price;
import com.jairoguo.goods.interfaces.rest.dto.GoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Jairo Guo
 */
@Mapper(uses = {Price.class})
public interface GoodsPriceAssembler {
    GoodsPriceAssembler INSTANCE = Mappers.getMapper(GoodsPriceAssembler.class);

    Price toProductPrice(GoodsDTO goodsDTO);

}
