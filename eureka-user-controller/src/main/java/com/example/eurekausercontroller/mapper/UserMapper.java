package com.example.eurekausercontroller.mapper;

import com.example.pojo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where phone = #{phone} and password =#{password}")
    public User getUserByUser(User user);
}
