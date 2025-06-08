package com.banxia;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {


    /**
     * 生成JWT
     */
    @Test
    public void testGenerateJWT() {

        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name","张三");
        dataMap.put("age",18);

        String banxia = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "banxia") // 签名的算法和密钥
                .addClaims(dataMap) // 附加数据
                .setExpiration(new Date(System.currentTimeMillis() + 1)) //设置过期时间
                .compact();// 生成令牌

        System.out.println(banxia);

    }

    /**
     * 解析JWT
     */
    @Test
    public void testParseJWT() {

        String s = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5byg5LiJIiwiYWdlIjoxOCwiZXhwIjoxNzQ3MDYzNzM2fQ.pQhWukZpGopFPvv7C0al6dYnz7REYVVOh-NocYZSlk0";

        Claims banxia = Jwts.parser() // 解密
                .setSigningKey("banxia") // 设置密钥
                .parseClaimsJws(s) // 设置被解密的令牌
                .getBody(); // 获取附加数据

        System.out.println(banxia);

    }
}
