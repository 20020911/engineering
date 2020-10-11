package com.example.ucenter.util;

import com.example.utils.AddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Configuration
public class KaHao {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void token(){
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
    public static void main(String[] args){
        KaHao kaHao = new KaHao();
        kaHao.dinDan("张三");
    }
    public String dinDan(String name){
        String ka = "";
        String date =simpleDateFormat.format(new Date());
        int s = Integer.parseInt(date);
        s = s-1;
        String count = "10001";
        String dd = (String)redisTemplate.opsForHash().get(name+date,date);
        if(dd==null){
            String ss =(String)redisTemplate.opsForHash().get(name+s,s);
            if(ss!=null && !ss.equals("")){
                redisTemplate.delete(name+ss);
            }
            redisTemplate.opsForHash().put(name+date,date,count);
           
        }else{
            System.out.println(dd);
            String ssa = dd;
            int css = Integer.parseInt(ssa)+1;
            count =String.valueOf(css);
            System.out.println(css);
            redisTemplate.opsForHash().put(name+date,date,count);
        }
        ka=date+count;
        return ka;
    }
    public String kaHao(){
        String a = "0202020202";
        String dd = (String)redisTemplate.opsForHash().get("kahao","kahao");
        if(dd==null){
            redisTemplate.opsForHash().put("kahao","kahao",a);
        }else{
            //生成随机数
            Random random = new Random();
            String sum = random.nextInt(10)+"";
            System.out.println(sum);
            int sums = Integer.parseInt(sum);


            String ssa = dd;
            int css = Integer.parseInt(ssa)+sums;
            a =String.valueOf(css);
            System.out.println(css);
            redisTemplate.opsForHash().put("kahao","kahao",a);
        }
        return a;
    }
}
