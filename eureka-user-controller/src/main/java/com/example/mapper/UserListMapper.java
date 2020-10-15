package com.example.mapper;

import com.example.entity.Role;
import com.example.entity.Status;
import com.example.entity.User;
import com.example.entity.User_Role;
import com.example.vo.UserListVo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface UserListMapper {
    List<UserListVo> getUserList(@Param("name")String name,@Param("status")Integer status,@Param("start")Integer start,@Param("size")Integer size);
    int getUserListCount(@Param("name")String name,@Param("status")Integer status);
    List<Status> getStatusList();
    @Select("select * from user where password=#{password} and id=#{id}")
    User getUser(@Param("password")String password,@Param("id")int id);
    @Update("UPDATE USER SET PASSWORD=#{password} where id=#{id}")
    int updPwd(@Param("password")String password,@Param("id")Integer id);
    @Update("UPDATE USER SET PASSWORD=#{name},status=#{status} where id=#{id}")
    int updInfo(@Param("name")String name,@Param("id")Integer id,@Param("status")Integer status);
    @Insert("INSERT INTO user_role(userId,roleId) VALUES(#{userId},#{roleId})")
    int addRole(User_Role user_role);
    @Delete("DELETE FROM user_role WHERE userId=#{userId} and roleId=#{roleId}")
    int delRole(User_Role user_role);
    @Select("select * from role")
    List<Role> getRoleList();
    @Select("select * from user_role where userId=#{userId}")
    List<User_Role> getURList(@Param("userId")Integer userId);
}
