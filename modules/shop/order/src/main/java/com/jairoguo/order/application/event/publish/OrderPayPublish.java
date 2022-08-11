package com.jairoguo.order.application.event.publish;

import com.jairoguo.order.domain.model.aggregate.Order;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jairo Guo
 */
@Service
public class OrderPayPublish {
    @Resource
    private StreamBridge streamBridge;

    public void initPay(Order order) {


        streamBridge.send("orderPayStatus-out-0", MessageBuilder.withPayload(order)
                .setHeader("x-delay", 1000 * 60 * 15)
                .build());
    }
}
