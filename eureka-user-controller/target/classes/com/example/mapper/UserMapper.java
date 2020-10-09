package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,phone,password,createdDate,type,status) value(#{name},#{phone},#{password},now(),1,1)")
    public int addUser(User user);

    @Select("select * from user where name = #{name}")
    public User remoteUser(@Param("name") String name);

    @Select("select * from user where phone = #{phone}")
    public User remoteUsers(@Param("phone") String phone);
}
