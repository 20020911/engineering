package com.example.ucenter.util;

import com.example.utils.AddressUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtil {
    @Resource
    static RedisTemplate<String,String> redisTemplate;
    @Test
    public static void main(String[] args){
        String token = "";
        try{
            String ip = AddressUtil.getLocalIpAddr();
            assert ip != null;
            token =(String)redisTemplate.opsForHash().get("token:"+ip,ip);
            if(token==null ||token.equals("")){

                System.out.println(token);
            }
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();

            System.out.println(token);
        }
        System.out.println(token);
    }



}