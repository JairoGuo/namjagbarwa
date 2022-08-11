package com.jairoguo.order.application.event.subscribe;

import com.jairoguo.order.application.service.OrderApplicationService;
import com.jairoguo.order.domain.model.aggregate.Order;
import com.jairoguo.order.domain.model.value.OrderStatusEnum;
import com.jairoguo.order.domain.repository.OrderRepository;
import com.jairoguo.order.infra.api.GoodsApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * @author Jairo Guo
 */
@Service
public class OrderPaySubscribe {

    @Resource
    private OrderApplicationService orderApplicationService;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private GoodsApiService goodsApiService;

    @Bean
    @Transactional
    Consumer<Order> orderPayStatus() {

        return orderPayload -> {

            Order order = orderApplicationService.getOrder(orderPayload.getOrderNumber().getId(), null);
            /*
            当定定未支付
             */
            if (order.getStatus() == OrderStatusEnum.UNPAID) {
                // 返还库存
                goodsApiService.returnStock(order.getSpecsAttributeId(), order.getTotalNum());

                // 标记订单未失效
                order.setStatus(OrderStatusEnum.CANCEL);
                orderRepository.update(order);
            }
        };

    }
}
