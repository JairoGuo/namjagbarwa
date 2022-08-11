package com.jairoguo.seller.domain.repository;

import com.jairoguo.common.base.Repository;
import com.jairoguo.seller.domain.model.aggregate.Store;
import com.jairoguo.seller.domain.model.entity.id.StoreNumber;

import java.util.List;

/**
 * @author Jairo Guo
 */
public interface StoreRepository extends Repository<Store, StoreNumber> {

    List<Store> list(StoreNumber storeNumber);
}
