package com.example.eurekaprojectcontroller.mapper;


import com.example.entity.User;
import com.example.pojo.io.project.DictionaryVO;
import com.example.pojo.io.project.PorjectListVO;
import com.example.pojo.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<PorjectListVO> getProjectList(@Param("name")String name,
                                       @Param("manager")String manager,
                                       @Param("state")Integer state,
                                       @Param("start")Integer start,
                                       @Param("size")Integer size);
    int getProjectListCount(@Param("name")String name,
                                       @Param("manager")String manager,
                                       @Param("state")Integer state);
    String getProjectListBack(@Param("name")String name,
                              @Param("manager")String manager,
                              @Param("state")Integer state);
    PorjectListVO getProject(@Param("id")Integer id);
    String getProjectBack(@Param("id")Integer id);
    int addProject(Project project);

    int updProject(Project project);
    int delProject(@Param("id")Integer id);
    List<DictionaryVO> stateList();
    List<Project> projectList();
    List<User> userList();
    @Select("select * from user where name =#{name}")
    public User getUserByName(@Param("name") String name);
}
