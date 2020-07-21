package com.erbaojun.springcloud.alibaba.service.impl;

import com.erbaojun.springcloud.alibaba.dao.StorageDao;
import com.erbaojun.springcloud.alibaba.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author outakaramoto
 * @create 2020/7/5 10:38 下午
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("----> StorageService中扣减库存");
        storageDao.decrease(productId, count);
        LOGGER.info("----> StorageService中扣减库存完成");
    }
}
