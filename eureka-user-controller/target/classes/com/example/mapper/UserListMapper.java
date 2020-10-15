package com.example.mapper;

import com.example.entity.Role;
import com.example.entity.Status;
import com.example.entity.User;
import com.example.entity.User_Role;
import com.example.vo.UserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select * from role")
    List<Role> getRoleList();
    @Select("select * from user_role where userId=#{userId}")
    List<User_Role> getURList(@Param("userId")Integer userId);
}
