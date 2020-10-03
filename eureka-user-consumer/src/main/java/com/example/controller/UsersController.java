package com.example.controller;

import com.example.mapper.UserMapperClient;
import com.example.pojo.pojo.User;
import com.example.pojo.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private UserMapperClient userMapperClient;

    @GetMapping("/user/sumer/hello")
    public ResponseData<Object> hello(){
        User user = new User();
        user.setPhone("15566667777");
        user.setPassword("123456");
        return userMapperClient.getUserByUser(user);
    }
}
