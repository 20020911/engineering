package com.example.service;

import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.vo.MenuListVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UsersService {
    public int addUser(User user);
    public User remoteUser(@Param("name") String name);
    public User remoteUsers(@Param("phone") String phone);
    public List<MenuListVo> menuList(String menuName,String url);
    public int menuListCount(String menuName,String url);
    public User getUserByName(@Param("name") String name);
    public int coutMenuListById(@Param("id") int id);
    public int deleteMenuById(@Param("id") int id);
    public Menu getMenuById(@Param("id") int id);
    public List<Menu> getMenuListById(@Param("id") int id);
    public List<Menu> getMenuList();
    public int updateMenuByMenu(Menu menu);
    public int addMenyByMenu(Menu menu);
    public List<Role> getRoleListByNameAndStatus(@Param("roleName") String roleName , @Param("status") int status, @Param("page")int page, @Param("pageSize")int pageSiz);

    public int countRoleListByNameAndStatus(@Param("roleName") String roleName ,@Param("status") int status);
    public int addRole(Role role);

    public int addRole_Menu(@Param("roleId")int roleId,@Param("menuId") int menuId);
    public int deleteRoleById(@Param("id")int id);
    public int deleteRoleMenuById(@Param("id") int id);
    public List<User> getUserListByRoleId(@Param("roleId") int roleId,@Param("name")String name,@Param("phone")String phone,@Param("page")int page,@Param("pageSize") int pageSize);
    public int countUserListByRoleId(@Param("roleId") int roleId,@Param("name")String name,@Param("phone")String phone);
    public int deleteUserRoleByUserIdAndRoleId(@Param("roleId") int roleId,@Param("userId")int userId);
}
