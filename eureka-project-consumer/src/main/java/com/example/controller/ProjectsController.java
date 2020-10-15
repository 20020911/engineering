package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.Project;
import com.example.mapper.ProjectMapperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class ProjectsController {
    @Autowired
    private ProjectMapperClient projectMapperClient;
    @RequestMapping(value = "/call/project/getList",method = RequestMethod.GET)
    public ResponseBean getList(
            @RequestParam(value = "manager",required = false)String manager,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "state",defaultValue = "0")Integer state,
            @RequestParam(value = "start",defaultValue = "0")int start,
            @RequestParam(value = "size",defaultValue = "2")int size) {
        //System.out.println(manager+"manager"+"----name"+name+"-----state"+state+"--start"+start+"---size"+size);
        System.out.println("12323");
        return projectMapperClient.getList(manager,name,state,start,size);
    }
    @RequestMapping(value = "/call/project/getPro",method = RequestMethod.GET)
    public ResponseBean getProject(
            @RequestParam(value = "id")Integer id){
        return projectMapperClient.getProject(id);
    }

    @RequestMapping(value = "/call/project/addPro",method = RequestMethod.POST)
    public ResponseBean addProject(Project project){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+project.toString());
        return projectMapperClient.addProject(project);
    }
    @RequestMapping(value = "/call/project/updPro",method = RequestMethod.POST)
    public ResponseBean updProject(Project project){
        System.out.println("uuuuuuuuuu"+project.toString());
        return projectMapperClient.updProject(project);
    }
    @RequestMapping("/call/project/delPro/{id}")
    public ResponseBean delProject(@PathVariable Integer id) {
        System.out.println("uuuuuuuuuuuuuuuuuuuuu"+id);
        return projectMapperClient.delProject(id);
    }

    @RequestMapping("/call/project/stateList")
    public ResponseBean stateList(){return projectMapperClient.stateList();}
    @RequestMapping("/call/project/projectList")
    public ResponseBean projectList(){return projectMapperClient.projectList();}
    @RequestMapping("/call/project/userList")
    public ResponseBean userList(){return projectMapperClient.userList();}

}
