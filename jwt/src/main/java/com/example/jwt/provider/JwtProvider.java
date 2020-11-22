package com.example.jwt.provider;

import cn.hutool.core.date.DateUtil;
import com.example.jwt.modules.AccessToken;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JWT工具类，一个完整的JwtToken由三部分组成：
 *  1、header：存放JwtToken的签名算法，以及 token的类型 => {"alg": "HS512", "typ": "JWT"}
 *  2、payload：主要存放用户名、创建时间、生成时间 => {"sub": "zhangsan", "createTime": 1489079981393, "exp": 1489684781}
 *  3、signature：生成算法：HMACSHA512(base64UrlEncoder(header) + "." + base64UrlEncoder(payload), secret)
 * @Author: wcg
 * @Date: 2020/11/22 18:36
 */
@Slf4j
@Component
public class JwtProvider {

    @Value("${app.jwt.securityKey:}")
    private String securityKey;
    @Value("${app.jwt.requestHeader:}")
    private String requestHeader;
    @Value("${app.jwt.tokenPrefix:}")
    private String tokenPrefix;
    @Value("${app.jwt.expiration:}")
    private Long expiration;
    
    
    /**
     * 从请求中获取 AccessToken
     * @param request
     * @return
     */
    public String getAccessToken(HttpServletRequest request) {
        return request.getHeader(requestHeader);
    }
    
    /**
     * 根据用户信息生成 AccessToken
     * @param userDetails
     * @return
     */
    public AccessToken createAccessTokenByUserDetails(UserDetails userDetails){
        return createAccessTokenByUserDetails(userDetails);
    }
    
    /**
     * 根据 subject 生成 accessToken
     * @param subject
     * @return
     */
    public AccessToken generateToken(String subject){
        Date now = new Date();
        Date expirateDate = new Date(now.getTime() + expiration*1000);
        String accessToken = tokenPrefix + Jwts.builder().setSubject(subject).setIssuedAt(now).setExpiration(expirateDate)
                .signWith(SignatureAlgorithm.HS512, securityKey).compact();
        log.warn("accessToken >>> {}", accessToken);
        return AccessToken.builder().loginAccount(subject).accessToken(accessToken).expirateDate(expirateDate).build();
    }
    
    /**
     * 验证 accessToken 是否有效
     * @param accessToken
     * @param userDetails
     * @return
     */
    public boolean validateAccessToken(String accessToken, UserDetails userDetails){
        Claims claims = getClaimsFromAccessToken(accessToken);
        return claims.getSubject().equals(userDetails.getUsername()) && !isAccessExpired(claims);
    }
    
    /**
     * 刷新 accessToken
     * @param oldToken
     * @return
     */
    public AccessToken refreshAccessToken(String oldToken) {
        String token = oldToken.substring(tokenPrefix.length());
        Claims claims = getClaimsFromAccessToken(token);
        return tokenRefreshJustBefore(claims) ?
                AccessToken.builder().loginAccount(claims.getSubject()).accessToken(oldToken).expirateDate(claims.getExpiration()).build()
                : generateToken(claims.getSubject());
    }
    
    /**
     * 从AccessToken中获取Subject
     * @param accessToken
     * @return
     */
    public String getSubjectFromAccessToken(String accessToken) {
        Claims claims = getClaimsFromAccessToken(accessToken);
        return claims == null ? null : claims.getSubject();
    }
    
    /**
     * 验证accessToken是否刚刚刷新过
     * @param claims
     * @return
     */
    private boolean tokenRefreshJustBefore(Claims claims) {
        Date refreshTime = new Date();
        if(refreshTime.after(claims.getExpiration()) && refreshTime.before(DateUtil.offsetSecond(claims.getExpiration(), 1800))){
            return true;
        }
        return false;
    }
    
    /**
     * 验证accessToken是否已经过期
     * @param claims
     * @return
     */
    private boolean isAccessExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
    
    /**
     * 从 AccessToken 中获取负载信息
     * @param accessToken
     * @return
     */
    private Claims getClaimsFromAccessToken(String accessToken) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(accessToken).getBody();
        } catch (Exception e) {
            log.error("JWT反解析失败, token已过期或不正确, token: [{}]", accessToken, e);
        }
        return claims;
    }
    
}
