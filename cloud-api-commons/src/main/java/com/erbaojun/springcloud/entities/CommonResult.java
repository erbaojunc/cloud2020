package com.erbaojun.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author outakaramoto
 * @create 2020/5/15 12:08 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //404 not_found
    private Integer code;
    private String message;
    private  T      data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
