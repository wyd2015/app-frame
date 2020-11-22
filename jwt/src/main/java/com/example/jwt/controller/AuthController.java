package com.example.jwt.controller;

import com.example.appframe.common.RestResult;
import com.example.jwt.model.vo.LoginInfo;
import com.example.jwt.provider.JwtProvider;
import com.example.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: wcg
 * @Date: 2020/11/22 19:49
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProvider jwtProvider;
    
    @PostMapping("/login")
    public RestResult login(
            @RequestParam("loginAccount") String loginAccount,
            @RequestParam("password") String password){
        return authService.login(loginAccount, password);
    }
    
    @PostMapping("/logout")
    public RestResult logout(){
        return authService.logout();
    }
    
    @PostMapping("/refresh")
    public RestResult refreshAccessToken(HttpServletRequest request){
        return authService.refreshAccessToken(jwtProvider.getAccessToken(request));
    }
    
}
