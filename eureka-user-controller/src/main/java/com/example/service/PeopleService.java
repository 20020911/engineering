package com.example.service;

import com.example.entity.Department;
import com.example.vo.PeopleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeopleService {
    List<PeopleVo> getPeopleList(@Param("name")String name, @Param("size")Integer size, @Param("start")Integer start);
    int getPeopleListCount(@Param("name")String name);
    List<Department> getDepList();
    int delPeople(@Param("id")Integer id);
}
