package com.erbaojun.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author outakaramoto
 * @create 2020/7/4 11:43 下午
 */
@MapperScan("com.erbaojun.springcloud.alibaba.dao")
@Configuration
public class MybatisConfig {
}
