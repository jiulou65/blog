package com.zx.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

    /**
     * 创建cookie
     * @param name
     * @param value
     * @param expire 单位为分钟
     * @return
     */
    public static Cookie createCookie(String name, String value, String path, int expire){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(expire*60);
        cookie.setPath(path);
        return cookie;
    }

    /**
     * 删除指定cookie
     * @param name
     */
    public static void removeCookie(String name){
        Cookie cookie = new Cookie(name,null);
        cookie.setMaxAge(0);
    }


    public static Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)){
                return cookies[i];
            }
        }
        return null;
    }


}
