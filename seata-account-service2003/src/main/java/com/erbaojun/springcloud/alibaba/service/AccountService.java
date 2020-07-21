package com.erbaojun.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @Author outakaramoto
 * @create 2020/7/5 11:13 下午
 */
public interface AccountService {

    void decrease(Long userId, BigDecimal money);

}
