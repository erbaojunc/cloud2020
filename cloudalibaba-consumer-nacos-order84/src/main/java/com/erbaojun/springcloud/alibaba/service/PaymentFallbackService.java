package com.erbaojun.springcloud.alibaba.service;

import com.erbaojun.springcloud.entities.CommonResult;
import com.erbaojun.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author outakaramoto
 * @create 2020/7/1 10:28 下午
 */
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回，------PaymentFallbackService",new Payment());
    }
}
