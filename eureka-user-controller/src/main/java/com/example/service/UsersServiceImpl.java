package com.example.service;

import com.example.entity.User;
import com.example.mapper.UsereMapper;
import com.example.vo.MenuListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsereMapper usereMapper;
    @Override
    public int addUser(User user) {
        return usereMapper.addUser(user);
    }

    @Override
    public User remoteUser(String name) {
        return usereMapper.remoteUser(name);
    }

    @Override
    public User remoteUsers(String phone) {
        return usereMapper.remoteUsers(phone);
    }

    @Override
    public List<MenuListVo> menuList(String menuName, int status) {
        return usereMapper.menuList(menuName,status);
    }

    @Override
    public int menuListCount(String menuName, int status) {
        return usereMapper.menuListCount(menuName,status);
    }

    @Override
    public User getUserByName(String name) {
        return usereMapper.getUserByName(name);
    }
}
