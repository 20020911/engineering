package com.example.service;

import com.example.entity.User;
import com.example.mapper.UsereMapper;
import com.example.vo.MenuListVo;
import com.example.vo.MenuNodeVo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
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
        List<MenuListVo> menuListVos =usereMapper.menuList(menuName,status);
        if(!CollectionUtils.isEmpty(menuListVos)){
            Collections.sort(menuListVos, MenuListVo.order());
        }
        return menuListVos;
    }

    @Override
    public int menuListCount(String menuName, int status) {
        return usereMapper.menuListCount(menuName,status);
    }

    @Override
    public User getUserByName(String name) {
        return usereMapper.getUserByName(name);
    }

    @Override
    public int coutMenuListById(int id) {
        return usereMapper.coutMenuListById(id);
    }
}
