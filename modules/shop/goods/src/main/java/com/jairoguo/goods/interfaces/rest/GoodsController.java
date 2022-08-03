package com.jairoguo.goods.interfaces.rest;

import com.jairoguo.goods.application.bo.GoodsBO;
import com.jairoguo.goods.application.service.GoodsApplicationService;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.dto.GoodsDTO;
import com.jairoguo.goods.dto.GoodsNumberDTO;
import com.jairoguo.goods.interfaces.rest.convert.GoodsConvert;
import com.jairoguo.goods.interfaces.rest.convert.GoodsToVO;
import com.jairoguo.goods.interfaces.rest.dto.BuyDTO;
import com.jairoguo.goods.vo.GoodsVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsApplicationService goodsApplicationService;

    @PostMapping("add")
    public void add(@RequestBody GoodsDTO goodsDTO) {
        Goods goods = GoodsConvert.convertToGoods(goodsDTO);
        GoodsBO goodsBO = GoodsBO.builder().goods(goods).build();
        goodsApplicationService.addGoods(goodsBO);

    }

    @PostMapping("get")
    public GoodsVO getGoods(@RequestBody GoodsNumberDTO goodsNumberDTO) {
        Goods goods = goodsApplicationService.getGoods(goodsNumberDTO.id(), goodsNumberDTO.number());
        return GoodsToVO.convertToGoodsVO(goods);
    }

    @PostMapping("list")
    public List<GoodsVO> list() {

        return GoodsToVO.convertToGoodsList(goodsApplicationService.goodsList());
    }

    @PostMapping("deductions/{id}/{count}")
    public void deductions(@PathVariable Long id, @PathVariable Long count) {
        goodsApplicationService.deductions(id, count);
    }

    @PostMapping("buy")
    public void buy(@RequestBody BuyDTO buyDTO) {
        goodsApplicationService.buy(buyDTO.goodsId(), buyDTO.specsAttributeId(), buyDTO.number());
    }

}
