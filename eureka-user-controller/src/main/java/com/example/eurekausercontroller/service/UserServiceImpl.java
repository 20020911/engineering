package com.example.eurekausercontroller.service;

import com.example.eurekausercontroller.mapper.UserMapper;
import com.example.pojo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;


    @Override
    public User getUserByUser(User user) {
        return userMapper.getUserByUser(user);
    }
}
