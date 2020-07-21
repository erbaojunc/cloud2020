package com.erbaojun.springcloud.alibaba.controller;

import com.erbaojun.springcloud.alibaba.domain.CommonResult;
import com.erbaojun.springcloud.alibaba.domain.Order;
import com.erbaojun.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author outakaramoto
 * @create 2020/7/4 11:40 下午
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功!");
    }
}
