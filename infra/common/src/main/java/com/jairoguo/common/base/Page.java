package com.jairoguo.common.base;

import java.util.List;

/**
 * @author Jairo Guo
 */
public interface Page<T> {

    Long getTotal();

    List<T> getData();

    Long getSize();

    Long getCurrent();
}
