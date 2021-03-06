package com.erbaojun.springcloud.service;

import com.erbaojun.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author outakaramoto
 * @create 2020/5/15 1:04 上午
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
}
