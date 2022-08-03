package com.jairoguo.goods.interfaces.rest;

import com.jairoguo.goods.application.service.GoodsSpecsApplicationService;
import com.jairoguo.goods.domain.model.entity.SpecsAttribute;
import com.jairoguo.goods.dto.GoodsNumberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("specs")
public class GoodsSpecsController {

    @Resource
    private GoodsSpecsApplicationService goodsSpecsApplicationService;

    @PostMapping("get")
    public SpecsAttribute getSpecsAttribute(@RequestBody GoodsNumberDTO goodsNumberDTO) {
        return goodsSpecsApplicationService.getGoodsSpecs(goodsNumberDTO.specsAttributeId());
    }
}
