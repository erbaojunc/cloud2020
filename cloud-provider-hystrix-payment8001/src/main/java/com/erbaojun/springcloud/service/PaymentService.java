package com.erbaojun.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author outakaramoto
 * @create 2020/5/31 11:43 下午
 */
@Service
public class PaymentService {

    /**
     * 正常访问，肯定ok的方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_OK,id: "+id+"\t"+"哈哈!";
    }

    /**
     * http://localhost:8001/payment/hystrix/timeout/1
     * @HystrixCommand报异常后如何处理：
     *  一旦调用服务方法失败并抛出了错误信息后，
     *  会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //设置这个线程的超时时间是3s，3s内是正常的业务逻辑，超过3s调用fallbackMethod指定的方法进行处理
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        //设置等待5秒
        int timeNumber = 2;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id: "+id+"\t"+"哈哈!"+"耗时 :"+timeNumber+"秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池: "+Thread.currentThread().getName()+" 系统繁忙或者程序运行报错，请稍后再试 paymentInfo_TimeOutHandler,id: "+id+"\t"+"o(╥﹏╥)o!";
    }

    //服务熔断------
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期 "
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if (id < 0){
            throw new RuntimeException("***id 不能为负数");
        }
        String SerialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+ SerialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id 不能负数，请稍后再试~ id:"+id;
    }

}
