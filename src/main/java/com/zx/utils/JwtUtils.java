package com.zx.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
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

    public static String createJWT(User user){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 30);

        String token = JWT.create()
                .withClaim("name", user.getUsername())
                .withClaim("password", user.getPassword())
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(key));
        return token;
    }

    public static boolean parseToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
        try {
            DecodedJWT verify = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
