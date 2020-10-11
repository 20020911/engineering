package com.example.utils;


public class TokenUtil {
    private static String token;
    static final ThreadLocal<String> TOKEN = new ThreadLocal<>();

    public static ThreadLocal<String> getTOKEN() {
        return TOKEN;
    }


    public static String getToken() {
        return token;
    }

    public static void setToken( String token) {
        TokenUtil.token = token;
    }
}
