package com.example.eurekaprojectcontroller.controller;

import com.example.eurekaprojectcontroller.service.ProjectService;
import com.example.pojo.io.project.PorjectListVO;
import com.example.pojo.pojo.Project;
import com.example.pojo.util.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public ResponseData<Object> getList(
            @RequestParam(value = "manager",required = false)String manager,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "state",defaultValue = "0")Integer state){
        ResponseData data = new ResponseData();
        try {
            List<PorjectListVO> list = projectService.getProjectList(name,manager,state);
            if(list!=null){
                for (PorjectListVO p:list){
                    System.out.println(p.toString());
                }
                data.setCode(200);
                data.setData(list);
                data.setMsg("查询成功");
                System.out.println("11111");
            }else{
                data.setCode(400);
                data.setMsg("未找到数据");
                System.out.println("22222");
            }
        }catch (Exception e){
            data.setCode(500);
            data.setMsg("异常出错");
            System.out.println("33333");
            e.printStackTrace();
        }
        return data;
    }
    @RequestMapping(value = "/getPro",method = RequestMethod.GET)
    public ResponseData<Object> getProject(
            @RequestParam(value = "id")Integer id){
        ResponseData data = new ResponseData();
        try {
            PorjectListVO pro = projectService.getProject(id);
            if(pro!=null){
                data.setCode(200);
                data.setData(pro);
                data.setMsg("查询成功");
                System.out.println("11111");
            }else{
                data.setCode(400);
                data.setMsg("未找到数据");
                System.out.println("22222");
            }
        }catch (Exception e){
            data.setCode(500);
            data.setMsg("异常出错");
            System.out.println("33333");
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping("/addPro")
    public ResponseData<Object> addProject(@RequestBody Project project){
        ResponseData data = new ResponseData();
        try {
            int count = projectService.addProject(project);
            if(count>0){
                data.setMsg("添加成功");
                data.setCode(200);
                data.setData(count);
            }else{
                data.setMsg("添加失败");
                data.setCode(400);
            }
        }catch (Exception e){
            data.setMsg("异常错误");
            data.setCode(500);
            e.printStackTrace();
        }
        return data;
    }
    @RequestMapping("/updPro")
    public ResponseData<Object> updProject(@RequestBody Project project){
        ResponseData data = new ResponseData();
        try {
            int count = projectService.updProject(project);
            if(count>0){
                data.setMsg("修改成功");
                data.setCode(200);
                data.setData(count);
            }else{
                data.setMsg("修改失败");
                data.setCode(400);
            }
        }catch (Exception e){
            data.setMsg("异常错误");
            data.setCode(500);
            e.printStackTrace();
        }
        return data;
    }
    @RequestMapping("/delPro/{id}")
    public ResponseData<Object> delProject(@PathVariable Integer id){
        ResponseData data = new ResponseData();
        try {
            int count = projectService.delProject(id);
            if(count>0){
                data.setMsg("删除成功");
                data.setCode(200);
                data.setData(count);
            }else{
                data.setMsg("删除失败");
                data.setCode(400);
            }
        }catch (Exception e){
            data.setMsg("异常错误");
            data.setCode(500);
            e.printStackTrace();
        }
        return data;
    }

}