package com.example.service;

import com.example.entity.User;
import com.example.vo.MenuListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService {
    public int addUser(User user);
    public User remoteUser(@Param("name") String name);
    public User remoteUsers(@Param("phone") String phone);
    public List<MenuListVo> menuList(String menuName,int status);
    public int menuListCount(String menuName,int status);
    public User getUserByName(@Param("name") String name);
    public int coutMenuListById(@Param("id") int id);
}
