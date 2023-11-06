package com.example.server;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ServerApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void testBcy() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admin = passwordEncoder.encode("admin");
        System.out.println(admin);
        String admin2 = passwordEncoder.encode("admin");
        System.out.println(admin2);
        boolean matches = passwordEncoder.matches("admin", admin);
        System.out.println(matches);

    }
    @Test
    public void testJwt(){
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 10);
        System.out.println(now);
        System.out.println(newTime); //min

        Map<String,Object> payload = new HashMap<String,Object>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put("userName", "zhangsan");
        payload.put("passWord", "666889");

        String key = "aabb";
        String token = JWTUtil.createToken(payload, key.getBytes());
        System.out.println(token);
    }

    @Test
    public void verifyJwt(){
        String key = "aabb";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzV29yZCI6IjY2Njg4OSIsIm5iZiI6MTY5NTYwMjA4MywiZXhwIjoxNjk1NjAyNjgzLCJ1c2VyTmFtZSI6InpoYW5nc2FuIiwiaWF0IjoxNjk1NjAyMDgzfQ.KpVuaDd_pwRmKCKMCTt_SvYYLivz3r8vCsqy68N3gFc";
        JWT jwt = JWTUtil.parseToken(token);

        boolean verifyKey = jwt.setKey(key.getBytes()).verify();
        System.out.println(verifyKey);

        boolean verifyTime = jwt.validate(0);
        System.out.println(verifyTime);
    }
    @Test
    public void testRootPath(){
        String property = System.getProperty("user.home");
        System.out.println(property);
    }

}
