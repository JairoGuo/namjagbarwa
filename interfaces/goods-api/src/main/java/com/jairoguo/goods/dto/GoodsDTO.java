package com.jairoguo.goods.dto;

import com.jairoguo.common.base.RequestBody;

/**
 * @author Jairo Guo
 */


public record GoodsDTO(

        String name,
        // Long categoryId,
        // String[] keywords,
        String introduction,
         // String storeId,
        // String detail,
        String cover,
        // String[] images,
        //  Integer sortWeight,
        //  BigDecimal giveIntegral,
        // Boolean multiSpecs,
        // List<SpecsDTO> specsList,
        // List<SpecsAttributeDTO> specsAttributeList
        SpecsAttributeDTO specsAttribute
        // String content,
        // Boolean isShow,
        // String shippingTemplateId
) implements RequestBody {
}

