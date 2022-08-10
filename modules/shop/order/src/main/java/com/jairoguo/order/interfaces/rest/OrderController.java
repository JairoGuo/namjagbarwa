package com.jairoguo.order.interfaces.rest;

import com.jairoguo.common.param.PageParam;
import com.jairoguo.common.result.PageResultBody;
import com.jairoguo.order.application.service.OrderApplicationService;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.dto.OrderDTO;
import com.jairoguo.order.interfaces.rest.convert.OrderConvert;
import com.jairoguo.order.interfaces.rest.dto.OrderNumberDTO;
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

    /**
     * 添加订单
     * @param orderDTO 订单参数
     */
    @PostMapping("add")
    public void add(@RequestBody OrderDTO orderDTO) {
        Order order = OrderConvert.convertToOrder(orderDTO);
        orderApplicationService.createOrder(order);
    }

    /**
     * 获取订单信息
     * @param orderNumber 订单号
     * @return Order
     */
    @PostMapping("get/{orderNumber}")
    public Order get(@PathVariable OrderNumberDTO orderNumber) {
        return orderApplicationService.getOrder(orderNumber.orderId(), orderNumber.orderNumber());
    }


    @PostMapping("list")
    public PageResultBody<Order> getList(@RequestBody PageParam param) {

        return orderApplicationService.getOrderList(param);
    }

}
