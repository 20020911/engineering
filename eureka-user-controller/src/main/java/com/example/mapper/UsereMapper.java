package com.example.mapper;

import com.example.entity.Menu;
import com.example.entity.User;
import com.example.vo.MenuListVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsereMapper {
    @Insert("insert into user(name,phone,password,createdDate,type,status) value(#{name},#{phone},#{password},now(),1,1)")
    public int addUser(User user);

    @Select("select * from user where name = #{name}")
    public User remoteUser(@Param("name") String name);

    @Select("select * from user where name =#{name}")
    public User getUserByName(@Param("name") String name);

    @Select("select * from user where phone = #{phone}")
    public User remoteUsers(@Param("phone") String phone);

    public List<User> userList();

    public List<MenuListVo> menuList(@Param("menuName")String menuName, @Param("status") int status);

    public int menuListCount(@Param("menuName")String menuName, @Param("status") int status);
    @Select("select count(*) from menu where parentId=#{id}")
    public int coutMenuListById(@Param("id") int id);
}
