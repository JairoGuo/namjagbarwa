package com.jairoguo.storage.domain.model.entity.id;

import com.jairoguo.common.base.Id;
import lombok.Getter;

/**
 * @author Jairo Guo
 */

@Getter
public class UserId implements Id {

    private Long id;

    private UserId() {
    }

    public static UserId create() {
        return new UserId();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
