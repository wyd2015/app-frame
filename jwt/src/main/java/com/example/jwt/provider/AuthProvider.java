package com.example.jwt.provider;

import com.example.jwt.model.po.SysUser;
import com.example.jwt.model.vo.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @Author: wcg
 * @Date: 2020/11/22 19:31
 */
public class AuthProvider {
    
    public static UserDetail getUserDetails(){
        return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    public static SysUser getUserInfo() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSysUser();
    }
    
    public static String getLoginAccount(){
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
    
    public static Integer getUserId() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }
    
    public static List<String> getAuthorities() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles();
    }
    
}
