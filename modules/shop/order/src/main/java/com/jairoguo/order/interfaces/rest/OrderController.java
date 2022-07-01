package com.jairoguo.order.interfaces.rest;

import com.jairoguo.order.application.service.OrderApplicationService;
import com.jairoguo.order.dto.OrderDTO;
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

    }

    @GetMapping("t")
    public String t() {
        orderApplicationService.test();
        return "ttt";
    }

}
