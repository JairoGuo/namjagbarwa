package com.jairoguo.common.param;

/**
 * @author Jairo Guo
 */
public class PageParam {

    private Long currentPage;
    private Long pageSize;

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
