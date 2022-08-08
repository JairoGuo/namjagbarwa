package com.jairoguo.common.result;

import com.jairoguo.common.base.Page;

/**
 * @author Jairo Guo
 */
public class PageResult {

    public static <T> PageResultBody<T> of(Page<T> page) {
        return PageResultBody.<T>builder()
                .data(page.getData())
                .currentPage(page.getCurrent())
                .pageSize(page.getSize())
                .total(page.getTotal())
                .build();
    }

    private PageResult() {
    }
}
