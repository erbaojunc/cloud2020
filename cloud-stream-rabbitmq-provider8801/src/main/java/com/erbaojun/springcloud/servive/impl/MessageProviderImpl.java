package com.erbaojun.springcloud.servive.impl;

import com.erbaojun.springcloud.servive.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.UUID;

/**
 * @Author outakaramoto
 * @create 2020/6/15 11:48 下午
 */
//这不是传统的service,这是和rabbitmq打交道的，不需要加注解@Service
//这里不掉dao，掉消息中间件的service
//信道channel和exchange绑定在一起
@EnableBinding(Source.class)   //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("****serial"+serial);
        return null;
    }
}
