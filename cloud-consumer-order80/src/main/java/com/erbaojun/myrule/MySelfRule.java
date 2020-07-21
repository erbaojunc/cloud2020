package com.erbaojun.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author outakaramoto
 * @create 2020/5/27 11:52 下午
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule muRule(){
        //随机
        return new RandomRule();
    }
}
