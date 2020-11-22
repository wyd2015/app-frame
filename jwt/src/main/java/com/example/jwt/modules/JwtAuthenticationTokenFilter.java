package com.example.jwt.modules;

import cn.hutool.core.util.StrUtil;
import com.example.jwt.provider.JwtProvider;
import com.example.jwt.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wcg
 * @Date: 2020/11/22 19:56
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtProvider jwtProvider;
    @Value("${app.jwt.tokenPrefix:}")
    private String tokenPrefix;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(">>>>> JWT过滤器通过校验请求头accessToken <<<<<<");
        String accessToken = jwtProvider.getAccessToken(request);
        log.debug("filter token >>> {}", accessToken);
        if(StrUtil.isNotEmpty(accessToken) && accessToken.startsWith(tokenPrefix)){
            String authToken = accessToken.substring(tokenPrefix.length());
            String loginAccount = jwtProvider.getSubjectFromAccessToken(authToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginAccount);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
    }
    
}
