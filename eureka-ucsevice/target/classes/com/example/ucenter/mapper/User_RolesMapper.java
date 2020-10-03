package com.example.ucenter.mapper;

import com.example.entity.Role;
import com.example.entity.User_Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface User_RolesMapper {
    @Select("select * from user_role where userId = #{userId}")
    public List<User_Role> getUserRoleListByRole(@Param("userId") int id);
}
