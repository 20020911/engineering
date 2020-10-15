package com.example.mapper;

import com.example.bean.ResponseBean;
import com.example.config.FeignConfig;
import com.example.entity.Project;
import com.example.fallback.ProjectsMapperClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(configuration = FeignConfig.class,value = "eureka-project-controller",fallback = ProjectsMapperClient.class)
public interface ProjectMapperClient {
    @RequestMapping(value = "/project/getList",method = RequestMethod.GET)
    ResponseBean getList(
            @RequestParam(value = "manager",required = false)String manager,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "state",defaultValue = "0")Integer state,
            @RequestParam(value = "start",defaultValue = "0")int start,
            @RequestParam(value = "size",defaultValue = "2")int size
    );
    @RequestMapping(value = "/project/getPro",method = RequestMethod.GET)
    ResponseBean getProject(
            @RequestParam(value = "id")Integer id);
    @RequestMapping(value = "/project/addPro",method = RequestMethod.POST)
    ResponseBean addProject(@RequestBody Project project);
    @RequestMapping(value = "/project/updPro",method = RequestMethod.POST)
    ResponseBean updProject(@RequestBody Project project);
    @RequestMapping(value = "/project/delPro/{id}",method = RequestMethod.POST)
    ResponseBean delProject(@PathVariable Integer id);
    @RequestMapping(value = "/project/stateList",method = RequestMethod.GET)
    ResponseBean stateList();
    @RequestMapping(value = "/project/projectList",method = RequestMethod.GET)
    ResponseBean projectList();
    @RequestMapping(value = "/project/userList",method = RequestMethod.GET)
    ResponseBean userList();

}
