package com.jairoguo.goods.infra.repository.database.assembler;


import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.domain.model.value.Attribute;
import com.jairoguo.goods.domain.model.value.Detail;
import com.jairoguo.goods.infra.factory.GoodsFactory;
import com.jairoguo.goods.infra.repository.database.po.GoodsAttributePO;
import com.jairoguo.goods.infra.repository.database.po.GoodsDetailPO;
import com.jairoguo.goods.infra.repository.database.po.GoodsPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Jairo Guo
 */
@Mapper(componentModel = "spring", uses = {GoodsFactory.class})
public interface GoodsPersistentAssembler {

    GoodsPersistentAssembler INSTANCE = Mappers.getMapper(GoodsPersistentAssembler.class);

    @Mapping(target = "keyword", ignore = true)
    @Mapping(target = "price", source = "price.sellPrice")
    @Mapping(target = "goodsNumber", source = "goodsNumber.number")
    GoodsPO toProductPO(Goods goods);

    List<Goods> toGoodsList(List<GoodsPO> goodsPOList);

    List<GoodsAttributePO> toProductAttributeListPO(List<Attribute> attributeList);

    GoodsDetailPO toProductDetailPO(Detail detail);

}