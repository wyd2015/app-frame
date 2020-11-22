package com.example.appframe.controller;

import com.example.appframe.annotation.ResponseResultBody;
import com.example.appframe.common.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wcg
 * @Date: 2020/11/22 15:27
 */
@RestController
@ResponseResultBody
public class AppTestController {
    
    private Map<String, Object> map = new HashMap<>();
    
    @GetMapping("/json")
    public Map<String, Object> restResult(){
        map.put("name", "王者荣耀");
        map.put("level", 2);
        return map;
    }
    
    
    @GetMapping("/error")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }
    
}
