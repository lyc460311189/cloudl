package com.lyc.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liyuchun
 * @version 1.0
 * @description: 返回结果类
 * @date 2022/5/16 14:40
 */

@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    private boolean success;

    private int code;

    private String msg;

    private T data;


    public static ResponseResult successResult() {
        return new ResponseResult(true,ResultCode.SUCCESS.getCode(), "ok", "");
    }

    public static <T> ResponseResult<T> successResult(T data) {
        return new ResponseResult(true,ResultCode.SUCCESS.getCode(), "ok", data);
    }

    public static <T> ResponseResult<T> failResult() {
        return new ResponseResult(false,ResultCode.SERVICE_EXCEPTION.getCode(), "fail", "");
    }

    public static <T> ResponseResult<T> failResult(String errorMsg) {
        return new ResponseResult(false,ResultCode.SERVICE_EXCEPTION.getCode(), errorMsg, "");
    }

    public static <T> ResponseResult<T> failResult(T data) {
        return new ResponseResult(false,ResultCode.SERVICE_EXCEPTION.getCode(), "fail", data);
    }
    public static <T> ResponseResult<T> failResult(ResultCode resultCode){
        return new ResponseResult(false,resultCode.getCode(),resultCode.getMessage(),"");
    }
    public static <T> ResponseResult<T> failResult(ResultCode resultCode,String errorMsg){
        return new ResponseResult(false,resultCode.getCode(),errorMsg,"");
    }
    public static  ResponseResult successResultMsg(String msg){
        return new ResponseResult(true,ResultCode.SUCCESS.getCode(),msg,"");
    }
    public static <T> ResponseResult<T> successResultMsg(String msg,T data){
        return new ResponseResult<T>(true,ResultCode.SUCCESS.getCode(),msg,data);
    }
}
