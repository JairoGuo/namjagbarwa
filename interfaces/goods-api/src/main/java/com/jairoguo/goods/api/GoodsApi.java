package com.jairoguo.goods.api;

import com.jairoguo.goods.dto.GoodsNumberDTO;
import com.jairoguo.goods.vo.GoodsVO;
import com.jairoguo.goods.vo.SpecsAttributeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jairo Guo
 */

@FeignClient(name = "goods-server")
public interface GoodsApi {

    @PostMapping("/goods/get")
    GoodsVO getGoodsSpecs(@RequestBody GoodsNumberDTO goodsNumberDTO);

    @PostMapping("/goods/deductions/{id}/{count}")
    void deductions(@PathVariable("id") Long id, @PathVariable("count") Long count);

    @PostMapping("/specs/get")
    SpecsAttributeVO getSpecsAttribute(@RequestBody GoodsNumberDTO goodsNumberDTO);
}
