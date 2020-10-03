package com.example.eurekaprojectconsumer.controller;

import com.example.mapper.ProjectMapperClient;
import com.example.pojo.pojo.Project;
import com.example.pojo.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/call/project")
public class ProjectController {
    @Autowired
    private ProjectMapperClient projectMapperClient;
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public ResponseData<Object> getList(
            @RequestParam(value = "manager",required = false)String manager,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "state",defaultValue = "0")Integer state,
            @RequestParam(value = "start",defaultValue = "0")int start,
            @RequestParam(value = "size",defaultValue = "2")int size) {
        System.out.println(manager+"manager"+"----name"+name+"-----state"+state+"--start"+start+"---size"+size);
        return projectMapperClient.getList(manager,name,state,start,size);
    }
    @RequestMapping(value = "/getPro",method = RequestMethod.GET)
    public ResponseData<Object> getProject(
            @RequestParam(value = "id")Integer id){
        return projectMapperClient.getProject(id);
    }

    @RequestMapping("/addPro")
    public ResponseData<Object> addProject(@RequestBody Project project){
        return projectMapperClient.addProject(project);
    }
    @RequestMapping("/updPro")
    public ResponseData<Object> updProject(@RequestBody Project project){
        return projectMapperClient.updProject(project);
    }
    @RequestMapping("/delPro/{id}")
    public ResponseData<Object> delProject(@PathVariable Integer id) {
        return projectMapperClient.delProject(id);
    }

    @RequestMapping("/stateList")
    public ResponseData<Object> stateList(){return projectMapperClient.stateList();}

}
