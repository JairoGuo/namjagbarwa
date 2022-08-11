package com.jairoguo.seller.application.service;

import com.jairoguo.seller.domain.model.aggregate.Store;
import com.jairoguo.seller.domain.model.entity.id.StoreNumber;
import com.jairoguo.seller.domain.repository.StoreRepository;
import com.jairoguo.seller.infra.api.AuthApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jairo Guo
 */
@Service
public class StoreApplicationService {


    @Resource
    private AuthApiService authApiService;

    @Resource
    private StoreRepository storeRepository;

    public void apply(String storeName) {

        authApiService.checkLogIn();
        Store store = Store.create();
        store.setName(storeName);
        storeRepository.save(store);

    }

    public List<Store> getStoreList() {
        authApiService.checkLogIn();
        StoreNumber storeNumber = new StoreNumber();
        storeNumber.setUserId(authApiService.getUserId());
        return storeRepository.list(storeNumber);

    }
}
