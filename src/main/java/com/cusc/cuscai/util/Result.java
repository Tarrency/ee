package com.cusc.cuscai.util;

/**
 * 统一返回
 */
public class Result {
    //状态码
    private Integer code;
    //描述信息
    private String msg;
    //返回数据
    private Object data;

    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //返回正确
    public static Result success(Object data) {
        return new Result(0, null, data);
    }

    //返回正确
    public static Result success(String msg, Object data) {
        return new Result(0, msg, data);
    }

    //返回错误
    public static Result fail(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    //返回错误
    public static Result fail(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }
}
