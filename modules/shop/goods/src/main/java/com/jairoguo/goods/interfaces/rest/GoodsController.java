package com.jairoguo.goods.interfaces.rest;

import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import com.jairoguo.goods.application.bo.GoodsBO;
import com.jairoguo.goods.application.service.GoodsApplicationService;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.interfaces.rest.assembler.GoodsAssembler;
import com.jairoguo.goods.interfaces.rest.dto.GoodsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */

@RestController
@RequestMapping("")
public class GoodsController {

    @Resource
    private GoodsApplicationService goodsApplicationService;

    @PostMapping("add")
    public void add(@RequestBody GoodsDTO goodsDTO) {

        Goods goods = GoodsAssembler.INSTANCE.toProduct(goodsDTO);
        GoodsBO goodsBO = GoodsBO.builder().goods(goods).build();
        goodsApplicationService.addGoods(goodsBO);

    }

    @PostMapping("list")
    public ResultBody<List<Goods>> list() {
        return Result.success(goodsApplicationService.goodsList());
    }
}
