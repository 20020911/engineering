package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.User;
import com.example.mapper.UserMapperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sumer")
public class UsersController {
    @Autowired
    private UserMapperClient userMapperClient;
    @PostMapping("/addUser")
    public ResponseBean addUser( User user){
       return  userMapperClient.addUser(user);
    }
    @PostMapping("/remoteUser")
    public ResponseBean remoteUser(@RequestParam String name){
        return userMapperClient.remoteUser(name);
    }
    @PostMapping("/remoteUsers")
    public ResponseBean remoteUsers(@RequestParam String phone){
        return userMapperClient.remoteUsers(phone);
    }
    @PostMapping("/menuList")
    public ResponseBean menuList(@RequestParam String menuName,@RequestParam(value = "status",defaultValue = "-1",required = false) int status){
        return userMapperClient.menuList(menuName,status);
    }
    @GetMapping("/init")
    public ResponseBean init(){
        return userMapperClient.init();
    }
    @GetMapping("/menu/countMenuList/{id}")
    public ResponseBean countMenuList(@PathVariable("id") int id){
        return userMapperClient.countMenuList(id);
    }
}
