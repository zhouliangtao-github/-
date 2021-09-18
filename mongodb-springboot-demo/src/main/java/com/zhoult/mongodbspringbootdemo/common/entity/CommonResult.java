package com.zhoult.mongodbspringbootdemo.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 面如冠玉、出类拔萃
 * @version 1.0
 * @date 2021/9/13 上午10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;
    public CommonResult(int code, String message) {
       this( code,  message,null);
    }
}
