package com.erbaojun.springcloud.alibaba.service;

/**
 * @Author outakaramoto
 * @create 2020/7/5 10:34 下午
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
