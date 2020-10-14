package com.example.mapper;

import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.vo.MenuListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsereMapper {
    @Insert("insert into user(name,phone,password,createdDate,type,status) value(#{name},#{phone},#{password},now(),1,1)")
    public int addUser(User user);

    @Select("select * from user where name = #{name}")
    public User remoteUser(@Param("name") String name);

    @Select("select * from user where name =#{name}")
    public User getUserByName(@Param("name") String name);

    @Select("select * from user where phone = #{phone}")
    public User remoteUsers(@Param("phone") String phone);

    public List<User> userList();

    public List<MenuListVo> menuList(@Param("menuName")String menuName, @Param("url") String url);

    public int menuListCount(@Param("menuName")String menuName, @Param("url") String url);
    @Select("select count(*) from menu where parentId=#{id}")
    public int coutMenuListById(@Param("id") int id);
    @Delete("delete from menu where id  = #{id}")
    public int deleteMenuById(@Param("id") int id);
    @Select("select * from menu where id = #{id}")
    public Menu getMenuById(@Param("id") int id);
    @Select("select * from menu where parentId = #{id}")
    public List<Menu> getMenuListById(@Param("id") int id);
    @Select("select * from menu")
    public List<Menu> getMenuList();
    @Update("update menu set menuName = #{menuName},url=#{url},parentId=#{parentId},orderNum = #{orderNum},updatedDate = now(),updatedBy=#{updatedBy} where id =#{id}")
    public int updateMenuByMenu(Menu menu);
    @Insert("insert into menu(menuName,url,parentId,orderNum,createdDate,createdBy,type,open,available) values(#{menuName},#{url},#{parentId},#{orderNum},now(),#{createdBy},0,1,0)")
    public int addMenyByMenu(Menu menu);

    public List<Role> getRoleListByNameAndStatus(@Param("roleName") String roleName ,@Param("status") int status,@Param("page")int page,@Param("pageSize")int pageSiz);

    public int countRoleListByNameAndStatus(@Param("roleName") String roleName ,@Param("status") int status);

    public int addRole(Role role);
    @Insert("insert into role_menu(roleId,menuId) value(#{roleId},#{menuId})")
    public int addRole_Menu(@Param("roleId")int roleId,@Param("menuId") int menuId);
    @Delete("delete from role where id =#{id}")
    public int deleteRoleById(@Param("id")int id);
    @Delete("delete from role_menu where roleId = #{id}")
    public int deleteRoleMenuById(@Param("id") int id);
    public List<User> getUserListByRoleId(@Param("roleId") int roleId,@Param("name")String name,@Param("phone")String phone,@Param("page")int page,@Param("pageSize") int pageSize);
    public int countUserListByRoleId(@Param("roleId") int roleId,@Param("name")String name,@Param("phone")String phone);
    @Delete("delete from user_role where userId = #{userId} and roleId=#{roleId}")
    public int deleteUserRoleByUserIdAndRoleId(@Param("roleId") int roleId,@Param("userId")int userId);
}
