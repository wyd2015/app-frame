package com.example.jwt.modules;

import com.example.appframe.common.RestResult;
import com.example.appframe.common.RestStatus;
import com.example.jwt.utils.JacksonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 权限不足异常处理类
 * </p>
 * 当用户尝试访问需要权限才能的REST资源而权限不足的时候，
 * 将调用此方法发送403响应以及错误信息
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().println(JacksonUtil.toJsonString(new RestResult(RestStatus.FORBIDDEN, e.getMessage(), null)));
        response.getWriter().flush();
    }
}
