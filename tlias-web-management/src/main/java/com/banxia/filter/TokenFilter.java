package com.banxia.filter;

import com.banxia.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;



// 拦截所有请求
//@WebFilter(urlPatterns = "/*")
// 日志
@Slf4j
public class TokenFilter implements Filter {
    // 初始化方法
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter初始化成功");
    }

    // 过滤行为
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求...");

        HttpServletRequest  request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        获取到请求路径
        String requestURI = request.getRequestURI();
//        判断路径是否有/login ，如果有就放行
        if (requestURI.contains("/login")) {
            log.info("是登录操作，放行了");
            filterChain.doFilter(request, response);
            return;
        }
//        获取请求头的Token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("令牌为空");
//            响应410
            response.setStatus(401);
            return;
        }
//        校验令牌
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("令牌校验失败");
            response.setStatus(401);
            return;
        }
//        校验通过，放行
        filterChain.doFilter(request, response);

    }

    // 销毁方法
    @Override
    public void destroy() {
        log.info("Filter销毁成功");
    }
}
