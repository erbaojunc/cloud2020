package com.erbaojun.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author outakaramoto
 * @create 2020/5/15 12:00 上午
 */
@Data
@AllArgsConstructor //全参
@NoArgsConstructor //空参
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
