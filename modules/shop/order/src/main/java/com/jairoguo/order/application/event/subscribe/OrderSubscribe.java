package com.jairoguo.order.application.event.subscribe;

import com.jairoguo.order.application.service.OrderApplicationService;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.dto.OrderDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * @author Jairo Guo
 */

@Service
public class OrderSubscribe {

    @Resource
    private OrderApplicationService orderApplicationService;

    @Bean
    Consumer<OrderDTO> placeOrder() {

        return orderDTO -> {
            Order order = Order.create();
            // order.setGoodsId(orderDTO.specsAttributeId());
            order.setSpecsAttributeId(orderDTO.specsAttributeId());
            if (orderDTO.total() == null) {
                order.setTotalNum(1L);
            } else {
                order.setTotalNum(orderDTO.total());
            }
            order.setUserId(orderDTO.userId());
            order.setPrice(orderDTO.price());
            orderApplicationService.createOrder(order);
        };

    }
}
