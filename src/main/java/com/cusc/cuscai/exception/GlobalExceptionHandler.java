package com.cusc.cuscai.exception;

import com.cusc.cuscai.util.Result;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    /**
     * 业务异常
     */
    @ExceptionHandler(GlobalException.class)
    public Result globalExeception(GlobalException e) {
        return Result.fail(e.getCode(), e.getMsg());
    }

    /**
     * 参数异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, TypeMismatchException.class,
            MissingServletRequestPartException.class, HttpRequestMethodNotSupportedException.class, MultipartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result msgNotReadable(Exception e) {
        return Result.fail(400, "请求参数不匹配");
    }

    /**
     * 未知异常
     */
    @ExceptionHandler
    public Result unkonwException(Exception e) {
        return Result.fail(500, "未知异常");
    }
}
