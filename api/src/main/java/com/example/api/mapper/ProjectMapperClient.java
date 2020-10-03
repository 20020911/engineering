package com.example.api.mapper;

import com.example.pojo.io.project.PorjectListVO;
import com.example.pojo.pojo.Project;
import com.example.pojo.util.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "eureka-project-controller")
@RequestMapping(value = "/project")
public interface ProjectMapperClient {
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    ResponseData<Object> getList(
            @RequestParam(value = "manager",required = false)String manager,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "state",defaultValue = "0")Integer state,
            @RequestParam(value = "start",defaultValue = "0")int start,
            @RequestParam(value = "size",defaultValue = "2")int size
    );
    @RequestMapping(value = "/getPro",method = RequestMethod.GET)
    ResponseData<Object> getProject(
            @RequestParam(value = "id")Integer id);
    @RequestMapping(value = "/addPro",method = RequestMethod.POST)
    ResponseData<Object> addProject(@RequestBody Project project);
    @RequestMapping(value = "/updPro",method = RequestMethod.POST)
    ResponseData<Object> updProject(@RequestBody Project project);
    @RequestMapping(value = "/delPro/{id}",method = RequestMethod.POST)
    ResponseData<Object> delProject(@PathVariable Integer id);
    @RequestMapping(value = "/stateList",method = RequestMethod.GET)
    ResponseData<Object> stateList();

}
