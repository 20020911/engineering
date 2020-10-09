package com.example.controller;

import com.example.bean.ActiveUser;
import com.example.bean.ResponseBean;
import com.example.entity.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user/addUser")
    public ResponseBean addUser(@RequestBody User user){
        try{
            int count = userService.addUser(user);
            if(count>0){
                return ResponseBean.success("注册成功！");
            }else{
                return ResponseBean.error("注册失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @PostMapping("/user/remoteUser")
    public ResponseBean remoteUser(@RequestParam String name){
        try{
            User user = userService.remoteUser(name);
            if(user == null){
                return ResponseBean.success("可以使用");
            }else{
                return ResponseBean.error(4001,"名称重复");
            }
        }catch (Exception e){
            return ResponseBean.error(500,"发生异常");
        }
    }
    @PostMapping("/user/remoteUsers")
    public ResponseBean remoteUsers(@RequestParam String phone){
        try{
            User user = userService.remoteUsers(phone);
            if(user == null){
                return ResponseBean.success("可以使用");
            }else{
                return ResponseBean.error(4001,"手机号重复");
            }
        }catch (Exception e){
            return ResponseBean.error(500,"发生异常");
        }
    }

}
