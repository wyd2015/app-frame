package com.example.jwt.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.jwt.dao.RelRoleUserMapper;
import com.example.jwt.model.po.SysRole;
import com.example.jwt.model.po.SysUser;
import com.example.jwt.model.vo.UserDetail;
import com.example.jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wcg
 * @Date: 2020/11/22 17:17
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private RelRoleUserMapper roleUserMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录验证，用户名 >>> {}", username);
        SysUser sysUser = userService.getUserByLoginAccount(username);
        if(ObjectUtil.isEmpty(sysUser)){
            throw new UsernameNotFoundException("用户名不存在，登录失败！");
        }
    
        UserDetail userDetail = new UserDetail();
        userDetail.setSysUser(sysUser);
        List<SysRole> roleList = roleUserMapper.listRoleByUserId(sysUser.getUserId());
        userDetail.setRoleList(roleList);
        return userDetail;
    }
}
