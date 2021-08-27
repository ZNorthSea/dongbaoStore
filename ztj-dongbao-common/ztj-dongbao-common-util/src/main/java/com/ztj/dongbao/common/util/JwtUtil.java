package com.ztj.dongbao.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "zhaotj";  // 盐

    /**
     * 创建token
     * @param subject 要放入token的参数
     * @return token字符串
     */
    public static String createToken(String subject){
        String token = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return token;
    }

    /**
     * 校验token
     * @param token 要检验的token
     * @return token中放入的参数
     */
    public static String parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
    }

}
