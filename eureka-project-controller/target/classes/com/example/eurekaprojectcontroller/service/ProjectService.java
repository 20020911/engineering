package com.example.eurekaprojectcontroller.service;

import com.example.entity.User;
import com.example.eurekaprojectcontroller.mapper.ProjectMapper;
import com.example.pojo.io.project.DictionaryVO;
import com.example.pojo.io.project.PorjectListVO;
import com.example.pojo.pojo.Project;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService {
    @Resource
    private ProjectMapper projectMapper;
    public List<PorjectListVO> getProjectList(@Param("name")String name,
                                              @Param("manager")String manager,
                                              @Param("state")Integer state,
                                              @Param("start")Integer start,
                                              @Param("size")Integer size){
        return projectMapper.getProjectList(name,manager,state,(start-1)*size,size);
    }
    public int getProjectListCount(@Param("name")String name,
                                              @Param("manager")String manager,
                                              @Param("state")Integer state){
        return projectMapper.getProjectListCount(name,manager,state);
    }
    public PorjectListVO getProject(@Param("id")Integer id){
        return projectMapper.getProject(id);
    }
    public int addProject(Project project){
        project.setP_state(4);
        return projectMapper.addProject(project);
    }
    public int updProject(Project project){return projectMapper.updProject(project);}
    public int delProject(@Param("id")Integer id) {return projectMapper.delProject(id);}
    public List<DictionaryVO> stateList(){return projectMapper.stateList();}
    public List<Project> projectList(){return projectMapper.projectList();}
    public List<User> userList(){return projectMapper.userList();}
    public User getUserByName(@Param("name") String name){
        return projectMapper.getUserByName(name);
    }
}
