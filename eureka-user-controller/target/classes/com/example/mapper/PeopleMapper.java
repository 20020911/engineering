package com.example.mapper;

import com.example.entity.Department;
import com.example.entity.User;
import com.example.vo.PeopleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PeopleMapper {
    List<PeopleVo> getPeopleList(@Param("name")String name,@Param("size")Integer size,@Param("start")Integer start);
    int getPeopleListCount(@Param("name")String name);
    List<Department> getDepList();
    @Delete("delete from user where id=#{id}")
    int delPeople(@Param("id")Integer id);
}
