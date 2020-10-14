package com.example.service;

import com.example.entity.Menu;
import com.example.entity.Role;
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
    public List<MenuListVo> menuList(String menuName, String url) {
        List<MenuListVo> menuListVos =usereMapper.menuList(menuName,url);
        if(!CollectionUtils.isEmpty(menuListVos)){
            Collections.sort(menuListVos, MenuListVo.order());
        }
        return menuListVos;
    }

    @Override
    public int menuListCount(String menuName, String url) {
        return usereMapper.menuListCount(menuName,url);
    }

    @Override
    public User getUserByName(String name) {
        return usereMapper.getUserByName(name);
    }

    @Override
    public int coutMenuListById(int id) {
        return usereMapper.coutMenuListById(id);
    }

    @Override
    public int deleteMenuById(int id) {
        return usereMapper.deleteMenuById(id);
    }

    @Override
    public Menu getMenuById(int id) {
        return usereMapper.getMenuById(id);
    }

    @Override
    public List<Menu> getMenuListById(int id) {
        return usereMapper.getMenuListById(id);
    }
    @Override
    public List<Menu> getMenuList() {
        return usereMapper.getMenuList();
    }

    @Override
    public int updateMenuByMenu(Menu menu) {
        return usereMapper.updateMenuByMenu(menu);
    }

    @Override
    public int addMenyByMenu(Menu menu) {
        return usereMapper.addMenyByMenu(menu);
    }

    @Override
    public List<Role> getRoleListByNameAndStatus(String roleName, int status, int page, int pageSiz) {
        return usereMapper.getRoleListByNameAndStatus(roleName,status,page,pageSiz);
    }

    @Override
    public int countRoleListByNameAndStatus(String roleName, int status) {
        return usereMapper.countRoleListByNameAndStatus(roleName,status);
    }

    @Override
    public int addRole(Role role) {
        return usereMapper.addRole(role);
    }

    @Override
    public int addRole_Menu(int roleId, int menuId) {
        return usereMapper.addRole_Menu(roleId,menuId);
    }

    @Override
    public int deleteRoleById(int id) {
        return usereMapper.deleteRoleById(id);
    }

    @Override
    public int deleteRoleMenuById(int id) {
        return usereMapper.deleteRoleMenuById(id);
    }

    @Override
    public List<User> getUserListByRoleId(int roleId, String name, String phone, int page, int pageSize) {
        return usereMapper.getUserListByRoleId(roleId,name,phone,page,pageSize);
    }

    @Override
    public int countUserListByRoleId(int roleId, String name, String phone) {
        return usereMapper.countUserListByRoleId(roleId,name,phone);
    }

    @Override
    public int deleteUserRoleByUserIdAndRoleId(int roleId, int userId) {
        return usereMapper.deleteUserRoleByUserIdAndRoleId(roleId,userId);
    }
}
