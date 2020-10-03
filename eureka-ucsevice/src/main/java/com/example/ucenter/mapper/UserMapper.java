package com.example.ucenter.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface UserMapper extends Mapper<User> {
//    @Select("select * from user where name = #{name}")
//    User selectOne(User user);
//    @Select("select * from user where id = #{id}")
//    User selectByPrimaryKey(@Param("id") int id);
}
