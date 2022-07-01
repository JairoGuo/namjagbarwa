package com.jairoguo.goods.interfaces.rest;

import com.jairoguo.goods.application.bo.GoodsBO;
import com.jairoguo.goods.application.service.GoodsApplicationService;
import com.jairoguo.goods.domain.model.aggregate.Goods;
import com.jairoguo.goods.dto.GoodsDTO;
import com.jairoguo.goods.interfaces.rest.convert.GoodsConvert;
import com.jairoguo.goods.interfaces.rest.convert.GoodsToVO;
import com.jairoguo.goods.vo.GoodsVO;
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
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsApplicationService goodsApplicationService;

    @PostMapping("add")
    public void add(@RequestBody GoodsDTO goodsDTO) {
        System.out.println(goodsDTO);
        Goods goods = GoodsConvert.convertToGoods(goodsDTO);
        GoodsBO goodsBO = GoodsBO.builder().goods(goods).build();
        goodsApplicationService.addGoods(goodsBO);

    }

    @PostMapping("list")
    public List<GoodsVO> list() {

        return GoodsToVO.convertToGoodsList(goodsApplicationService.goodsList());
    }

}
