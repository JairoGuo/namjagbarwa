package com.jairoguo.seller.interfaces.rest;

import com.jairoguo.seller.application.service.StoreApplicationService;
import com.jairoguo.seller.domain.model.aggregate.Store;
import com.jairoguo.seller.interfaces.rest.dto.StoreApplyInfoDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */

@RestController
@RequestMapping("store")
public class StoreController {

    @Resource
    private StoreApplicationService storeApplicationService;

    // TODO 申请成为商家
    @PostMapping("apply")
    public void apply(@RequestBody StoreApplyInfoDTO storeApplyInfoDTO) {
        storeApplicationService.apply(storeApplyInfoDTO.storeName());
    }

    @GetMapping("get")
    public List<Store> getStoreList() {
        return storeApplicationService.getStoreList();
    }

    // TODO 更新店铺信息

}
