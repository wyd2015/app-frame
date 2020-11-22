package com.example.appframe.common;

import lombok.Getter;

/**
 * @Author: wcg
 * @Date: 2020/11/22 15:45
 */
@Getter
public class RestException extends Exception {
    
    private RestStatus restStatus;
    
    public RestException() {
        this(RestStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    public RestException(RestStatus restStatus) {
        super(restStatus.getMsg());
        this.restStatus = restStatus;
    }
}
