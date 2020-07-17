package com.cusc.cuscai.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回
 */
@Data
@ApiModel("返回体")
public class Result {
    //状态码
    @ApiModelProperty(value = "状态码")
    private Integer code;
    //描述信息
    @ApiModelProperty(value = "描述信息")
    private String msg;
    //返回数据
    @ApiModelProperty(value = "返回数据")
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

    //返回正确
    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    //返回正确hotspot_question_infohotspot_question_info
    public static Result success(int code, String msg) {
        return new Result(code, msg, null);
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
