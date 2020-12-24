package com.zx.interceptor;

import com.zx.utils.JwtUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 根据cookie的token比较判断用户是否登录
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("token")){
                String value = cookies[i].getValue();
                boolean b = JwtUtils.parseToken(value);
                if (b){
                    return true;
                }
                break;
            }
        }
        response.sendRedirect("/admin");
        return false;
    }
}
