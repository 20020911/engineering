package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bean.JWTUser;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

public class JWTUtils {
    private static final long EXPIRE_TIME=7*24*3600*1000;
    private static final String TOKEN_SECRET="Token";
    public static boolean verify(String token,String username,String secret){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){

            return false;
        }
    }
    public static String sign(String username,String secret){
        try{
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("username",username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e){
            return null;
        }
    }
    public static String getUsername(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }

    public static JWTUser getJwtUser(String token){
        try{
            JWTUser jwtUser = new JWTUser();
            DecodedJWT jwt = JWT.decode(token);
            jwtUser.setUserName(jwt.getClaim("username").asString());
            return jwtUser;
        }catch (JWTDecodeException e){
            return null;
        }
    }
    public static boolean isExpire(String token){
        DecodedJWT jwt = JWT.decode(token);
        return System.currentTimeMillis()>jwt.getExpiresAt().getTime();
    }
}
