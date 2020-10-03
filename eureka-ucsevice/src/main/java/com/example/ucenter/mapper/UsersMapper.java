package com.example.ucenter.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper {
    @Select("select * from user where name = #{name}")
    public User getUserByName(@Param("name")String name);
    @Select("select * from user where id = #{id}")
    User selectByPrimaryKey(@Param("id")int id);
    @Select("select * from user where name = #{name}")
    User selectOne(@Param("name") String name);
    @Select("select * from user where name =#{name} and password = #{password}")
    User getUserByNameAndPwd(@Param("name") String name,@Param("password") String password);
}
