package com.erbaojun.springcloud.alibaba.service.impl;

import com.erbaojun.springcloud.alibaba.dao.OrderDao;
import com.erbaojun.springcloud.alibaba.domain.Order;
import com.erbaojun.springcloud.alibaba.service.AccountService;
import com.erbaojun.springcloud.alibaba.service.OrderService;
import com.erbaojun.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author outakaramoto
 * @create 2020/7/4 11:10 下午
 */
@Service
@Slf4j
public class OderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    @Override
    //name随便命名，只要不重复即可
    //rollbackFor = Exception.class表示出现所有异常都回滚
    //rollbackFor表示哪些需要回滚
    //noRollbackFor表示哪些不需要回滚
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("-------->开始新建订单");
        //1.新建订单
        orderDao.create(order);

        log.info("--------->订单微服务开始调用库存，做扣减Count");
        //2.扣减库存
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--------->订单微服务开始调用库存，做扣减end");

        log.info("--------->订单微服务开始调用账户，做扣减Money");
        //3.扣减账户
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------->订单微服务开始调用账户，做扣减end");

        //4.修改订单的状态 从零到1，1代表已经完成
        log.info("-------->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("-------->修改订单状态结束");

        log.info("-------->下订单结束了，︿(￣︶￣)︿");
    }
}
