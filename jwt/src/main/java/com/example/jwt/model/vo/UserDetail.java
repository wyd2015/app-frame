package com.example.jwt.model.vo;

import cn.hutool.core.util.ObjectUtil;
import com.example.jwt.model.po.SysRole;
import com.example.jwt.model.po.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: wcg
 * @Date: 2020/11/22 21:13
 */
@Data
public class UserDetail implements UserDetails {
    
    private SysUser sysUser;
    private List<String> roles;
    private List<SysRole> roleList;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(ObjectUtil.isNotEmpty(grantedAuthorities)) return grantedAuthorities;
    
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> authorities = new ArrayList<>();
        roleList.forEach(role -> {
            authorities.add(role.getRoleName());
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        
        this.grantedAuthorities = grantedAuthorities;
        this.roles = authorities;
        return this.grantedAuthorities;
    }
    
    public Integer getUserId(){
        return sysUser.getUserId();
    }
    
    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }
    
    @Override
    public String getUsername() {
        return sysUser.getLoginAccount();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
