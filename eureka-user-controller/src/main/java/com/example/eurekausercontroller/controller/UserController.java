package com.example.eurekausercontroller.controller;

import com.example.eurekausercontroller.service.UserService;
import com.example.pojo.pojo.User;
import com.example.pojo.util.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user/hello")
    public ResponseData<Object> hello(@RequestBody User user){
        ResponseData<Object> rd = new ResponseData();
        try{
            User user1 = userService.getUserByUser(user);
            if(user1!=null){
                rd.setCode(200);
                rd.setMsg("查询成功");
                rd.setData(user1);
            }else{
                rd.setCode(500);
                rd.setMsg("查询失败");
                rd.setData(user1);
            }
        }catch (Exception e){
            e.printStackTrace();
            rd.setCode(500);
            rd.setMsg("发生异常");
            rd.setData(e.getMessage());
        }
        return rd;
    }
}
