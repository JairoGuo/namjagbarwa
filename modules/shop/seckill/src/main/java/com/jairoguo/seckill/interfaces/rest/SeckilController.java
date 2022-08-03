package com.jairoguo.seckill.interfaces.rest;

import com.jairoguo.seckill.application.service.SeckillApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("seckill")
public class SeckilController {
    @Resource
    private SeckillApplicationService seckillApplicationService;

    @PostMapping("seckill/{pathId}/{specsAttributeId}")
    public void seckill(@PathVariable Long specsAttributeId, @PathVariable String pathId) {
        seckillApplicationService.seckill(specsAttributeId, pathId);

    }

    @GetMapping("seckillPath/{seckillGoodsId}/{specsAttributeId}")
    public String path(@PathVariable Long specsAttributeId) {
        return seckillApplicationService.getSeckillPath(specsAttributeId);
    }
}
