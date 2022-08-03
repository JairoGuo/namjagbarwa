package com.jairoguo.order.api;

import com.jairoguo.order.dto.OrderDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jairo Guo
 */
public interface OrderApi {

    @PostMapping("/order/add")
    void add(@RequestBody OrderDTO orderDTO);
}
