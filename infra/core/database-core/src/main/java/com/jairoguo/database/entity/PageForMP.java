package com.jairoguo.database.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jairoguo.common.base.Entity;
import com.jairoguo.common.base.Page;
import com.jairoguo.common.base.PagePayload;

import java.util.List;

/**
 * @author Jairo Guo
 */
public class PageForMP<T> extends PagePayload<T> {

    public static <T extends Entity<?>> Page<T> of(IPage<T> page) {
        PageForMP<T> pageForMP = new PageForMP<>();
        pageForMP.total = page.getTotal();
        pageForMP.currentPage = page.getCurrent();
        pageForMP.pageSize = page.getSize();
        pageForMP.data = page.getRecords();
        return pageForMP;
    }

    public static <T extends Entity<?>, S extends BaseEntity> Page<T> of(IPage<S> page, List<T> data) {
        PageForMP<T> pageForMP = new PageForMP<>();
        pageForMP.total = page.getTotal();
        pageForMP.currentPage = page.getCurrent();
        pageForMP.pageSize = page.getSize();
        pageForMP.data = data;
        return pageForMP;
    }
}
