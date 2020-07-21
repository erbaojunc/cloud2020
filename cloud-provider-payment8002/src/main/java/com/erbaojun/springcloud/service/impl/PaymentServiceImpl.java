package com.erbaojun.springcloud.service.impl;

import com.erbaojun.springcloud.dao.PaymentDao;
import com.erbaojun.springcloud.entities.Payment;
import com.erbaojun.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author outakaramoto
 * @create 2020/5/15 1:06 上午
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
