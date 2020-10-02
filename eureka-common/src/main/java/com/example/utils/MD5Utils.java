package com.example.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Utils {
    public static String md5Encryption(String source,String salt){
        String algorithmName = "MD5";
        int hashIterations= 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName,source,salt,hashIterations);
        return simpleHash+"";
    }
}
