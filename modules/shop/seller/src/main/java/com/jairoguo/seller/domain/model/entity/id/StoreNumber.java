package com.jairoguo.seller.domain.model.entity.id;

import com.jairoguo.common.base.Id;
import lombok.Data;

/**
 * @author Jairo Guo
 */
@Data
public class StoreNumber implements Id {
    private Long id;
    private Long userId;

}
