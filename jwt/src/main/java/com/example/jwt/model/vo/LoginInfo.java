package com.example.jwt.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wcg
 * @Date: 2020/11/22 19:50
 */
@Data
public class LoginInfo {
    @NotBlank(message = "登录名不能为空！")
    private String loginAccount;
    @NotBlank(message = "登录密码不能为空！")
    private String password;
}
