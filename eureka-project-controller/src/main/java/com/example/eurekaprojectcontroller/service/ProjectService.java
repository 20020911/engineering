package com.example.eurekaprojectcontroller.service;

import com.example.eurekaprojectcontroller.mapper.ProjectMapper;
import com.example.pojo.io.project.PorjectListVO;
import com.example.pojo.pojo.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService {
    @Resource
    private ProjectMapper projectMapper;
    public List<PorjectListVO> getProjectList(@Param("name")String name,
                                              @Param("manager")String manager,
                                              @Param("state")Integer state){
        return projectMapper.getProjectList(name,manager,state);
    }
    public PorjectListVO getProject(@Param("id")Integer id){
        return projectMapper.getProject(id);
    }
    public int addProject(Project project){return projectMapper.addProject(project);}
    public int updProject(Project project){return projectMapper.updProject(project);}
    public int delProject(@Param("id")Integer id) {return projectMapper.delProject(id);}
}
