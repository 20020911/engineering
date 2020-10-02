package com.example.ucenter.mapper;

import com.example.entity.Role_Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Role_MenuMapper {
    @Select("select * from role_menu where roleId = #{role_id}")
    List<Role_Menu> selectByExample(@Param("role_id") int role_id);
}
