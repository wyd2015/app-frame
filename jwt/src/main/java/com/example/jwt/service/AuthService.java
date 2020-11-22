package com.example.jwt.service;

import com.example.appframe.common.RestResult;
import com.example.jwt.modules.AccessToken;
import com.example.jwt.provider.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @Author: wcg
 * @Date: 2020/11/22 19:36
 */
@Slf4j
@Service
public class AuthService {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    public RestResult login(String loginAccount, String password) {
        // 1 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(loginAccount, password);
        // 2 认证
        Authentication authentication = authenticationManager.authenticate(usernameAuthentication);
        // 3 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 4 生成自定义token
        AccessToken accessToken = jwtProvider.createAccessTokenByUserDetails((UserDetails) authentication.getPrincipal());
        // RedisUtil.set("LOGIN_ACCOUNT_"+loginAccout, loginAccout, 1800);
        return RestResult.success(accessToken);
    }
    
    public RestResult logout(){
        SecurityContextHolder.clearContext();
        return RestResult.success();
    }
    
    public RestResult refreshAccessToken(String accessToken){
        AccessToken accessToken1 = jwtProvider.refreshAccessToken(accessToken);
        return RestResult.success(accessToken1);
    }
}
