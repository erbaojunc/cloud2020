package com.erbaojun.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author outakaramoto
 * @create 2020/5/25 12:25 上午
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("{server.port}")
    private String serverPort;

    @GetMapping(value = "/zk/get")
    public  String paymentzk(){
        return  "Spring cloud with zk port: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
