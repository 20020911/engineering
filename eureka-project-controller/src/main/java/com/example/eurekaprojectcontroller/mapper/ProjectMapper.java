package com.example.eurekaprojectcontroller.mapper;

import com.example.pojo.io.project.PorjectListVO;
import com.example.pojo.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<PorjectListVO> getProjectList(@Param("name")String name,
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

}
