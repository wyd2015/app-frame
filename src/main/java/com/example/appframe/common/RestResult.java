package com.example.appframe.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.appframe.common.RestStatus.UNAUTHORIZED;

/**
 * 统一返回对象
 * @Author: wcg
 * @Date: 2020/11/22 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult<T> {
    
    private RestStatus status;
    private String msg;
    private T data;
    
    public RestResult(RestStatus status, T data) {
        this.status = status;
        this.data = data;
    }
    
    public static RestResult<Void> success() {
        return new RestResult<Void>(RestStatus.SUCCESS, null);
    }
    
    public static <T> RestResult<T> success(T data) {
        return new RestResult<>(RestStatus.SUCCESS, data);
    }
    
    public static <T> RestResult<T> success(RestStatus status, T data) {
        return status == null ? success(data) : new RestResult<T>(status, data);
    }
    
    public static <T> RestResult<T> failed() {
        return new RestResult<>(RestStatus.INTERNAL_SERVER_ERROR, null);
    }
    
    public static <T> RestResult<T> failed(RestStatus status, T data) {
        return status == null ? new RestResult<>(RestStatus.INTERNAL_SERVER_ERROR, null) : new RestResult<>(status, data);
    }
    
    public static <T> RestResult<T> failed(RestStatus status) {
        return failed(status, null);
    }
    
    public static <T> RestResult<T> failed(String msg) {
        return new RestResult<>(UNAUTHORIZED, msg,null);
    }
    
}
