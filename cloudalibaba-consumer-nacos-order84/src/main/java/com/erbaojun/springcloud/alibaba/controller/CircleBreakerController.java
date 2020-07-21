package com.erbaojun.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.erbaojun.springcloud.alibaba.service.PaymentService;
import com.erbaojun.springcloud.entities.CommonResult;
import com.erbaojun.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author outakaramoto
 * @create 2020/6/30 11:51 下午
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static  final  String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",
            fallback ="handlerFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    //如果出现exceptionsToIgnore中的异常，不运行fallback兜底方法。
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(
                SERVICE_URL + "/paymentSQL/" + id, CommonResult.class,id);

        if(id == 4){
            throw new IllegalArgumentException("IllegalArgument,非法参数异常...");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }

        return  result;
    }

    //fallback兜底
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"异常handlerFallback，exception内容： " + e.getMessage(), payment);
    }

    //blockHandler兜底
    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"blockHandler-sentinel 限流，BlockException： " + e.getMessage(), payment);
    }

    //=========openFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
