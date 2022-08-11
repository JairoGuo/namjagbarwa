package com.jairoguo.seller.domain.model.aggregate;

import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.seller.domain.model.entity.id.StoreNumber;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jairo Guo
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Store implements AggregateRoot<StoreNumber> {
    private StoreNumber storeNumber;
    private String name;
    private String introduce;

    public static Store create() {
        return new Store();
    }
}
