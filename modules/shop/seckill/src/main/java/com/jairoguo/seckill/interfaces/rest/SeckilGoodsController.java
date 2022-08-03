package com.jairoguo.seckill.interfaces.rest;

import com.jairoguo.seckill.application.service.SeckillApplicationService;
import com.jairoguo.seckill.domain.model.aggregate.SeckillGoods;
import com.jairoguo.seckill.interfaces.rest.convert.SeckillGoodsConvert;
import com.jairoguo.seckill.interfaces.rest.dto.SeckillGoodsDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */

@RestController
@RequestMapping("goods")
public class SeckilGoodsController {

    @Resource
    private SeckillApplicationService seckillApplicationService;


    @PostMapping("add")
    public void add(@RequestBody SeckillGoodsDTO seckillGoodsDTO) {

        seckillApplicationService.add(SeckillGoodsConvert.convertToSeckillGoods(seckillGoodsDTO));

    }

    @GetMapping("list")
    public List<SeckillGoods> list() {
        return seckillApplicationService.goodsList();
    }
}
