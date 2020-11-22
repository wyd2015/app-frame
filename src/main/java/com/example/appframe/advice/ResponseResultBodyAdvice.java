package com.example.appframe.advice;

import com.example.appframe.annotation.ResponseResultBody;
import com.example.appframe.common.RestException;
import com.example.appframe.common.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.WebUtils;

import java.lang.annotation.Annotation;

/**
 * @Author: wcg
 * @Date: 2020/11/22 15:39
 */
@Slf4j
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice {
    
    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;
    
    
    /**
     * 判断类或方法是否使用了 @ResponseResultBody注解
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }
    
    
    /**
     * 当类或方法使用@ResponseBody注解时，就会调用这个方法
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body instanceof RestResult ? body : RestResult.success(body);
    }
    
    
    /**
     * 提供对标准 Spring MVC的异常处理
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RestResult<?>> exceptionHandler(Exception e, WebRequest request){
        log.error("ExceptionHandler: {}", e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        return e instanceof RestException ? this.handleRestException((RestException) e, headers, request)
                : this.handleException((RestException) e, headers, request);
    }
    
    /**
     * 异常类统一处理
     * @param e
     * @param headers
     * @param request
     * @return
     */
    private ResponseEntity<RestResult<?>> handleException(RestException e, HttpHeaders headers, WebRequest request) {
        RestResult<Object> body = RestResult.failed(e.getRestStatus());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(e, body, headers, httpStatus, request);
    }
    
    /**
     * 对 RestException 类返回的返回结果的处理
     * @param e
     * @param headers
     * @param request
     * @return
     */
    private ResponseEntity<RestResult<?>> handleRestException(RestException e, HttpHeaders headers, WebRequest request) {
        RestResult<Object> body = RestResult.failed(e.getRestStatus());
        HttpStatus httpStatus = e.getRestStatus().getHttpStatus();
        return this.handleExceptionInternal(e, body, headers, httpStatus, request);
    }
    
    private ResponseEntity<RestResult<?>> handleExceptionInternal(RestException e, RestResult<Object> body, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
        if(HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatus)){
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, httpStatus);
    }
    
}
