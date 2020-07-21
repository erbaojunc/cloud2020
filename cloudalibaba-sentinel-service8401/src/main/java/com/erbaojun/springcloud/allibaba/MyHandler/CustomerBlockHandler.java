package com.erbaojun.springcloud.allibaba.MyHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.erbaojun.springcloud.entities.CommonResult;

/**
 * @Author outakaramoto
 * @create 2020/6/30 1:09 上午
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(  444, "按客户自定义,global handlerException-------1 ");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(  444, "按客户自定义,global handlerException-------2 ");
    }
}
