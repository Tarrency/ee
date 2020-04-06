package com.cusc.cuscai.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private Integer code;
    private String msg;
}
