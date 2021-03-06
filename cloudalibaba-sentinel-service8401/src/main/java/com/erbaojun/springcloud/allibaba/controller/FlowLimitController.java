package com.erbaojun.springcloud.allibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author outakaramoto
 * @create 2020/6/25 11:49 下午
 */
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"......testB");
        return "--------testB";
    }

    @GetMapping("/testD")
    public String testD() {
        log.info("testD 测试RT");
        int age=10/0;
        return "----- testD";
    }

    @GetMapping("/testE")
    public String testE() {
        log.info("testD 测试异常数");
        int age=10/0;
        return "----- testD 测试异常数";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHostkey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        return "-----testHotKey";
    }

    public String deal_testHostkey(String p1, String p2, BlockException e){
        return "-----deal_testHotKey";
    }

}
