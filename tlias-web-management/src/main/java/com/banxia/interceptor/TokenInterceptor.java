package com.banxia.interceptor;

import com.banxia.utils.CurrentHolder;
import com.banxia.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

//    拦截前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到了请求...");

//        获取请求头的Token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("令牌为空");
//            响应410
            response.setStatus(401);
            return false;
        }
//        校验令牌
        try {
            Claims claims = JwtUtils.parseJWT(token);
//            获取登录id信息，放入ThreadLocal
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户ID为：{}，存入ThreadLocal", empId);
        }catch (Exception e){
            log.info("令牌校验失败");
            response.setStatus(401);
            return false;
        }
//        校验通过，放行
        log.info("令牌校验通过");

        return true;

    }

    // 拦截后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle....");
    }

    // 请求处理完成后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //        删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
