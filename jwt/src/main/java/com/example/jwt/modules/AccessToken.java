package com.example.jwt.modules;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wcg
 * @Date: 2020/11/22 18:42
 */
@Data
@Builder
public class AccessToken {
    private String loginAccount;
    private String accessToken;
    private Date expirateDate;
}
