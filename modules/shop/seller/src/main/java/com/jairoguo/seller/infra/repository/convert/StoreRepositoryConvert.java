package com.jairoguo.seller.infra.repository.convert;

import com.jairoguo.seller.domain.model.aggregate.Store;
import com.jairoguo.seller.domain.model.entity.id.StoreNumber;
import com.jairoguo.seller.infra.repository.po.StorePO;

/**
 * @author Jairo Guo
 */
public class StoreRepositoryConvert {

    public static Store convertToStore(StorePO item) {
        if (item == null) {
            return null;
        }
        Store result = Store.create();
        StoreNumber storeNumber = new StoreNumber();
        storeNumber.setId(item.getId());
        storeNumber.setUserId(item.getUserId());
        result.setStoreNumber(storeNumber);
        result.setName(item.getName());
        result.setIntroduce(item.getIntroduce());
        return result;
    }
}
