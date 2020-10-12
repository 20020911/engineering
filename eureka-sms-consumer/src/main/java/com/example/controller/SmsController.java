package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.mapper.SmsMapperClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SmsController {
    @Autowired
    private SmsMapperClient smsMapperClient;
    @PostMapping("/sms/SendVerification")
    public ResponseBean register(@RequestParam String phone ){

       return  smsMapperClient.register(phone);
    }
    @PostMapping("/sms/yzm")
    public ResponseBean yzm(@RequestParam String yzm,@RequestParam String phone){
        return smsMapperClient.yzm(yzm,phone);
    }
}
