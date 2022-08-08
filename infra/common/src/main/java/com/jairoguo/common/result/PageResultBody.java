package com.jairoguo.common.result;

import com.jairoguo.common.base.PagePayload;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jairo Guo
 */
public class PageResultBody<T> extends PagePayload<T> implements Serializable {

    public PageResultBody(Builder<T> builder) {
        this.total = builder.getTotal();
        this.currentPage = builder.getCurrent();
        this.pageSize = builder.getSize();
        this.data = builder.getData();
    }

    public static <T> Builder<T> builder() {
        return new PageResultBody.Builder<>();
    }

    static class Builder<R> extends PagePayload<R> {


        public Builder() {
            this.total = null;
            this.currentPage = null;
            this.pageSize = null;
            this.data = null;
        }

        public Builder<R> total(Long total) {
            this.total = total;
            return this;
        }

        public Builder<R> currentPage(Long currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Builder<R> pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<R> data(List<R> data) {
            this.data = data;
            return this;
        }

        public PageResultBody<R> build() {
            return new PageResultBody<>(this);

        }
    }

}
