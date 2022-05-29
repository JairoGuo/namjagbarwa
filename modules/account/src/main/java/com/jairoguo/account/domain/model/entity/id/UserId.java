package com.jairoguo.account.domain.model.entity.id;

import com.jairoguo.common.base.Id;
import lombok.Data;

/**
 * @author Jairo Guo
 */
@Data
public class UserId implements Id {
    private Long id;

    private UserId() {
    }

    public static UserId create() {

        return new UserId();

    }
}
