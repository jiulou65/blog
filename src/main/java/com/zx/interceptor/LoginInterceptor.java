package com.zx.interceptor;

import com.zx.utils.CookieUtils;
import com.zx.utils.JwtUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 根据cookie的token比较判断用户是否登录
        Cookie ck = CookieUtils.getCookie(request, "token");
        if (ck != null){
            String token = ck.getValue();
            boolean b = JwtUtils.parseToken(token);
            if (b){
                request.setAttribute("uid",JwtUtils.getPayLoad(token).getClaim("id").asLong());
                return true;
            }
        }
        request.setAttribute("message","请您先登录");
        response.sendRedirect("/admin");
        return false;
    }
}
