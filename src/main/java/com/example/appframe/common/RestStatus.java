package com.example.appframe.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 返回状态码枚举类
 * @Author: wcg
 * @Date: 2020/11/22 15:08
 */
@Getter
@ToString
@AllArgsConstructor
public enum RestStatus {
    
    SUCCESS(HttpStatus.OK, 200, "OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, 403, "Forbidden"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal server error.");
    
    private HttpStatus httpStatus;
    private Integer code;
    private String msg;
    
}
