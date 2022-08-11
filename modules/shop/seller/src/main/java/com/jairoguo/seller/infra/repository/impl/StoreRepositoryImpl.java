package com.jairoguo.seller.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jairoguo.seller.domain.model.aggregate.Store;
import com.jairoguo.seller.domain.model.entity.id.StoreNumber;
import com.jairoguo.seller.domain.repository.StoreRepository;
import com.jairoguo.seller.infra.repository.convert.StoreRepositoryConvert;
import com.jairoguo.seller.infra.repository.mapper.StoreMapper;
import com.jairoguo.seller.infra.repository.po.StorePO;

import java.util.List;

/**
 * @author Jairo Guo
 */
public class StoreRepositoryImpl implements StoreRepository {
    private final StoreMapper storeMapper;

    public StoreRepositoryImpl(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Override
    public Boolean save(Store aggregate) {
        StorePO storePO = new StorePO();
        storePO.setName(aggregate.getName());
        return storeMapper.insert(storePO) == 1;
    }

    @Override
    public Store findById(StoreNumber id) {
        return null;
    }

    @Override
    public Boolean delete(StoreNumber id) {
        return null;
    }

    @Override
    public Boolean update(Store aggregate) {
        return null;
    }

    @Override
    public List<Store> list(StoreNumber storeNumber) {
        QueryWrapper<StorePO> storePOQueryWrapper = new QueryWrapper<>();

        storePOQueryWrapper.lambda()
                .eq(StorePO::getUserId, storeNumber.getUserId());

        return storeMapper
                .selectList(storePOQueryWrapper)
                .stream()
                .map(StoreRepositoryConvert::convertToStore)
                .toList();
    }
}
