package com.example.ucenter.mapper;

import com.example.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper  {
    @Select("select * from role where id = #{role_id}")
    Role findRolesByid(@Param("role_id")int role_id);
}
