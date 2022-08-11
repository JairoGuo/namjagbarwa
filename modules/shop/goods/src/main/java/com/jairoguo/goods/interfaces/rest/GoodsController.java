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
import javax.validation.Valid;
import java.util.List;

/**
 * @author Jairo Guo
 */

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsApplicationService goodsApplicationService;

    /**
     * 添加商品
     * @param goodsDTO 商品信息
     */
    @PostMapping("add")
    public void add(@RequestBody @Valid GoodsDTO goodsDTO) {
        Goods goods = GoodsConvert.convertToGoods(goodsDTO);
        GoodsBO goodsBO = GoodsBO.builder().goods(goods).build();
        goodsApplicationService.addGoods(goodsBO);

    }

    /**
     * 获取商品信息
     * @param goodsNumberDTO 商品号
     * @return 商品信息
     */
    @PostMapping("get")
    public GoodsVO getGoods(@RequestBody GoodsNumberDTO goodsNumberDTO) {
        Goods goods = goodsApplicationService.getGoods(goodsNumberDTO.id(), goodsNumberDTO.number());
        return GoodsToVO.convertToGoodsVO(goods);
    }

    @PostMapping("list")
    public List<GoodsVO> list() {

        return GoodsToVO.convertToGoodsList(goodsApplicationService.goodsList());
    }

    /**
     * 扣减库存
     * @param id 商品id
     * @param count 扣减数量
     */
    @PostMapping("deductions/{id}/{count}")
    public void deductions(@PathVariable Long id, @PathVariable Long count) {
        goodsApplicationService.deductions(id, count);
    }

    @PostMapping("buy")
    public void buy(@RequestBody BuyDTO buyDTO) {
        goodsApplicationService.buy(buyDTO.goodsId(), buyDTO.specsAttributeId(), buyDTO.number());
    }


    @PostMapping("returnStock/{specsId}/{count}")
    public void returnStock(@PathVariable Long specsId, @PathVariable Long count) {
        goodsApplicationService.returnStock(specsId, count);
    }


}
