package com.example.service;

import com.example.entity.Role;
import com.example.entity.Status;
import com.example.entity.User;
import com.example.entity.User_Role;
import com.example.vo.UserListVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserListService {
    List<UserListVo> getUserList(@Param("name")String name, @Param("status")Integer status, @Param("start")Integer start, @Param("size")Integer size);
    int getUserListCount(@Param("name")String name, @Param("status")Integer status);
    int updPwd(@Param("password")String password,@Param("id")Integer id);
    int updInfo(@Param("name")String name,@Param("id")Integer id,@Param("status")Integer status);
    List<Status> getStatusList();
    User getUser(@Param("password")String password,@Param("id")int id);
    List<Role> getRoleList();
    List<User_Role> getURList(@Param("userId")Integer userId);
    int addRole(User_Role user_role);
    int delRole(User_Role user_role);
}
