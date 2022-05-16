package com.lyc.test.model;

/**
 * 返回状态码
 */
public enum ResultCode {
    SUCCESS(1,"成功"),
    PARAM_IS_INVLTD(1001,"参数无效"),
    UNAUTHORIZED(1002,"用户未登陆"),
    USER_NOT_EXIST(2001,"用户不存在"),
    SERVICE_EXCEPTION(5001,"服务器内部错误");
    private Integer code;
    private String message;
    ResultCode(Integer code, String message){
        this.code=code;
        this.message=message;
    }
    public Integer getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
