package com.example.mapper;


import com.example.bean.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sms-ucservice")
public interface SmsMapperClient {
    @PostMapping("/SendVerificationCode")
    public ResponseBean register(@RequestParam String phone );
    @PostMapping("/yzm")
    public ResponseBean yzm(@RequestParam String yzm,@RequestParam String phone);
}
