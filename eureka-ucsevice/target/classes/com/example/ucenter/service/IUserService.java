package com.example.ucenter.service;

import cn.hutool.system.UserInfo;
import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.vo.MenuNodeVo;
import com.example.vo.UserInfoVo;

import java.util.List;

public interface IUserService {
    User findUserByName(String name);
    List<Role> findRolesByid(int id);
    List<Menu> fingMentsBuRoles(List<Role> roles);

    String login(String phone,String password);

    UserInfoVo info();

    List<MenuNodeVo> findMenu();
 }
