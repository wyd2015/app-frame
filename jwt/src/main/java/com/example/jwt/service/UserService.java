package com.example.jwt.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.jwt.dao.SysUserMapper;
import com.example.jwt.model.po.SysUser;
import com.example.jwt.model.qo.SysUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wcg
 * @Date: 2020/11/22 17:18
 */
@Service
public class UserService {
    @Autowired
    private SysUserMapper userMapper;
    
    public SysUser getUserByLoginAccount(String loginAccount){
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andLoginAccountEqualTo(loginAccount);
        List<SysUser> userList = userMapper.selectByExample(userExample);
        
        return ObjectUtil.isEmpty(userList) ? null : userList.get(0);
    }
}
