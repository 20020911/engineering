package com.example.service;

import com.example.entity.Role;
import com.example.entity.Status;
import com.example.entity.User;
import com.example.entity.User_Role;
import com.example.mapper.UserListMapper;
import com.example.vo.UserListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserListService{
    @Resource
    private UserListMapper userListMapper;
    @Override
    public List<UserListVo> getUserList(String name, Integer status, Integer start, Integer size) {
        return userListMapper.getUserList(name,status,start,size);
    }

    @Override
    public int getUserListCount(String name, Integer status) {
        return userListMapper.getUserListCount(name,status);
    }

    @Override
    public int updPwd(String password, Integer id) {
        return userListMapper.updPwd(password, id);
    }

    @Override
    public int updInfo(String name, Integer id, Integer status) {
        return updInfo(name, id, status);
    }

    @Override
    public List<Status> getStatusList() {
        return userListMapper.getStatusList();
    }

    @Override
    public User getUser(String password,int id) {
        return userListMapper.getUser(password,id);
    }

    @Override
    public List<Role> getRoleList() {
        return userListMapper.getRoleList();
    }

    @Override
    public List<User_Role> getURList(Integer userId) {
        return userListMapper.getURList(userId);
    }
}
