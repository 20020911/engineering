package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.Department;
import com.example.entity.Role;
import com.example.service.PeopleService;
import com.example.vo.PeopleVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PeopleController {
    @Resource
    private PeopleService peopleService;
    @RequestMapping(value = "/people/getPeopleList",method = RequestMethod.GET)
    public ResponseBean getPeopleList(@RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "start",defaultValue = "1")Integer start,
                                    @RequestParam(value = "size",defaultValue = "3")Integer size){
        try {
            start = (start-1)*size;
            List<PeopleVo> list = peopleService.getPeopleList(name,size,start);
            int count = peopleService.getPeopleListCount(name);
            System.out.println("-----------------");
            for (PeopleVo p:list){
                System.out.println("name-----"+p.getName());
            }
            if(list!=null){
                return ResponseBean.success(list,count);
            }else{
                return ResponseBean.success("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @RequestMapping(value = "/people/delPeople",method = RequestMethod.POST)
    public ResponseBean delPeople(@RequestParam(value = "id")Integer id){
        try {
            int count = peopleService.delPeople(id);
            if(count>0){
                return ResponseBean.success("成功");
            }else{
                return ResponseBean.success("失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @RequestMapping(value = "/people/getDepList",method = RequestMethod.GET)
    public ResponseBean getDepList(){
        try {
            List<Department> list = peopleService.getDepList();
            if(list!=null){
                return ResponseBean.success(list);
            }else{
                return ResponseBean.success("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
}
