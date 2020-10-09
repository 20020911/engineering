package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User remoteUser(String name) {
        return userMapper.remoteUser(name);
    }

    @Override
    public User remoteUsers(String phone) {
        return userMapper.remoteUsers(phone);
    }
}
