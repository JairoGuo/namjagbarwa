package com.jairoguo.order.interfaces.rest;

import com.jairoguo.order.application.service.OrderApplicationService;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.dto.OrderDTO;
import com.jairoguo.order.interfaces.rest.convert.OrderConvert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("order")
public class OrderController {


    @Resource
    private OrderApplicationService orderApplicationService;

    @PostMapping("add")
    public void add(@RequestBody OrderDTO orderDTO) {
        Order order = OrderConvert.convertToOrder(orderDTO);
        orderApplicationService.createOrder(order);
    }


}
