package com.example.service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsServiceImpl implements SmsService {
    /**
     * 发送验证码工具类
     *
     * @param phone 电话号码
     * @return 验证码
     */
    public String sms(String phone,String code) {
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
        restAPI.init("app.cloopen.com", "8883");
        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
        restAPI.setAccount("8aaf07087291adde0172ac4e28cf0ee0","52eb2cdbdeed40a38c663f53421ad631");
        // 请使用管理控制台中已创建应用的APPID。
        restAPI.setAppId("8aaf07087291adde0172ac4e29e60ee7");
        //设置需要发送的手机号和发送的验证码及过期时间
        result = restAPI.sendTemplateSMS(phone, "1", new String[]{code, "5"});
        if ("000000".equals(result.get("statusCode"))) {
            return code;
        } else {
            //异常返回输出错误码和错误信息
            String s = "错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg");
            return s;
        }
    }
}
