package com.example.jwt.controller;

import com.example.appframe.common.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wcg
 * @Date: 2020/11/22 22:27
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    
    @GetMapping("/test")
    public RestResult testApi(String name){
        return RestResult.success(name);
    }
    
}
