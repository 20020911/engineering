package com.example.mapper;

import com.example.bean.ResponseBean;
import com.example.entity.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "eureka-project-controller")
@RequestMapping(value = "/project")
public interface ProjectMapperClient {
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    ResponseBean getList(
            @RequestParam(value = "manager",required = false)String manager,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "state",defaultValue = "0")Integer state,
            @RequestParam(value = "start",defaultValue = "0")int start,
            @RequestParam(value = "size",defaultValue = "2")int size
    );
    @RequestMapping(value = "/getPro",method = RequestMethod.GET)
    ResponseBean getProject(
            @RequestParam(value = "id")Integer id);
    @RequestMapping(value = "/addPro",method = RequestMethod.POST)
    ResponseBean addProject(@RequestBody Project project);
    @RequestMapping(value = "/updPro",method = RequestMethod.POST)
    ResponseBean updProject(@RequestBody Project project);
    @RequestMapping(value = "/delPro/{id}",method = RequestMethod.POST)
    ResponseBean delProject(@PathVariable Integer id);
    @RequestMapping(value = "/stateList",method = RequestMethod.GET)
    ResponseBean stateList();
    @RequestMapping(value = "/projectList",method = RequestMethod.GET)
    ResponseBean projectList();
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    ResponseBean userList();

}
