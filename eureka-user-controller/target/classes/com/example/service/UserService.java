package com.example.service;

import com.example.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    public int addUser(User user);
    public User remoteUser(@Param("name") String name);
    public User remoteUsers(@Param("phone") String phone);
}
