package com.zx.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zx.po.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT的工具类
 */
@Component
public class JwtUtils {

    private static String key = "sah21odha!dq";

    /**
     * 创建JWT
     * @param user
     * @return
     */
    public static String createJWT(User user){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 30);

        String token = JWT.create()
                .withClaim("id",user.getId())
                .withClaim("name", user.getUsername())
                .withClaim("avatar", user.getAvatar())
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(key));
        return token;
    }

    /**
     * 判断JWT是否正确
     * @param token
     * @return
     */
    public static boolean parseToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
        try {
            DecodedJWT verify = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 获取token中的负载信息
     * @param token
     * @return
     */
    public static DecodedJWT getPayLoad(String token){
        return JWT.require(Algorithm.HMAC256(key)).build().verify(token);
    }

    /**
     * test
     * @param token
     * @return
     */
    public static Long getIdByToken(String token){
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(key)).build().verify(token);
        System.out.println(jwt.getClaim("id").asLong());
        System.out.println(jwt.getClaim("name").asString());
        System.out.println(jwt.getClaim("avatar").asString());
        return 1l;
    }

}
