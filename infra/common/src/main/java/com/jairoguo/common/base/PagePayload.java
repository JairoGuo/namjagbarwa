package com.jairoguo.common.base;

import java.util.List;

/**
 * @author Jairo Guo
 */
public class PagePayload<T> implements Page<T> {

    protected Long total;
    protected Long currentPage;
    protected Long pageSize;
    protected List<T> data;

    @Override
    public Long getTotal() {
        return total;
    }

    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public Long getSize() {
        return pageSize;
    }

    @Override
    public Long getCurrent() {
        return currentPage;
    }
}
