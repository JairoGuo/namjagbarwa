package com.jairoguo.goods.interfaces.rest.dto;

import com.jairoguo.common.base.RequestBody;

import java.util.List;

/**
 * @author Jairo Guo
 */


public record GoodsDTO(

        String name,
        Long categoryId,
        String[] Keywords,
        String introduction,
        String cover,
        String[] images,
        Boolean multiSpecs,
        List<SpecsDTO> specsList,
        List<SpecsAttributeDTO> specsAttributeList,
        String content,
        Boolean isShow,
        String shippingTemplateId
) implements RequestBody {
}

