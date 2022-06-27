package com.jairoguo.auth.domain.model.entity;

import lombok.Data;

/**
 * @author Jairo Guo
 * @date 2022/2/16 14:19
 */
@Data
public class Role {

    private Long id;
    private Integer parentId;
    private String code;
    private String name;
    private String intro;
}
