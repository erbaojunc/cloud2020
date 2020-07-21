package com.erbaojun.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author outakaramoto
 * @create 2020/7/5 11:08 下午
 */
public interface AccountDao {
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
