package com.rui.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import java.util.*;

import static cn.hutool.core.date.DateField.HOUR;

public class JwtUtil {

    private static final String KEY = "rui";

    public static String createToken(Map<String, Object> payload, DateField dateField, int time) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(dateField, time);

        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);

        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);

        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);

        String token = JWTUtil.createToken(payload, KEY.getBytes());

        return token;
    }

    public static Object parseToken(String token,String key) {
        JWT jwt = JWTUtil.parseToken(token);
        if(!jwt.setKey(KEY.getBytes()).verify()) {
            throw new RuntimeException("token 异常");
        }

        if(!jwt.validate(0)) {
            throw new RuntimeException("token 已过期请重新登录");
        }

        Object res = jwt.setKey(KEY.getBytes()).getPayload(key);
        return res;
    }



    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user",null);
        String token = createToken(map,HOUR,60);
        System.out.println(token);
        String name = (String)parseToken(token, "user");
        System.out.println(name);

    }

}