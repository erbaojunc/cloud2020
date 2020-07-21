package com.erbaojun.springcloud.controller;

import com.erbaojun.springcloud.servive.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author outakaramoto
 * @create 2020/6/16 12:08 上午
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String SendMessage(){
        return messageProvider.send();
    }
}
