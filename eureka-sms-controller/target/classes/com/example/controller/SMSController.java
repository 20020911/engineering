package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.service.SmsService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class SMSController {
    @Resource
    private SmsService smsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("/yzm")
    public ResponseBean yzm(@RequestParam String yzm,@RequestParam String phone){
        try{
            String yzms = (String)redisTemplate.opsForHash().get("yzm:"+phone,phone);
            if(yzms==null || yzms.equals("")){
                return ResponseBean.error(4001,"请重新发送验证码");
            }
            if(yzm.equals(yzms)){
                return ResponseBean.success("验证码正确");
            }else{
                return ResponseBean.error(400,"验证码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常");
        }

    }
    @PostMapping("/SendVerificationCode")
    public ResponseBean register( @RequestParam String phone ){
        try{
            int count=phone.length();
            if(count==11){
                Random random = new Random();
                boolean fult = true;
                String code="0000";
                while(fult){
                    System.out.println(fult);
                    code=random.nextInt(10000)+"";
                    if(code.length()!=4){
                      continue;
                    }else{
                       break;
                    }
                }

                smsService.sms(phone,code);
                redisTemplate.opsForHash().put("yzm:"+phone,phone,code);
                redisTemplate.expire("yzm:"+phone,300, TimeUnit.SECONDS);


                return ResponseBean.success("发送成功！");
            }else{
                return ResponseBean.error(400,"发送失败，号码不为11位");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"出现异常");
        }
    }
}
