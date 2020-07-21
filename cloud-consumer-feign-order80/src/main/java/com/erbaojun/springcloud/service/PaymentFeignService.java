package com.erbaojun.springcloud.service;

import com.erbaojun.springcloud.entities.CommonResult;
import com.erbaojun.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author outakaramoto
 * @create 2020/5/30 12:27 上午
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE") //指定调用哪个微服务
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}") //哪个地址
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id);
}
