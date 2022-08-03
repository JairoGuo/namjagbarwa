package com.jairoguo.seckill.application.event.publish;

import com.jairoguo.order.dto.OrderDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Jairo Guo
 */

@Service
public class OrderPublish {

    @Resource
    private StreamBridge streamBridge;

    public void placeOrder(Long userId, Long specsAttributeId, BigDecimal price) {


        streamBridge.send("placeOrder-out-0", new OrderDTO(specsAttributeId, userId, price, 1L));
    }
}
