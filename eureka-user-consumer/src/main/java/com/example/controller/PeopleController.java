package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.Department;
import com.example.mapper.PeopleMapperClient;
import com.example.vo.PeopleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/call")
public class PeopleController {
    @Autowired
    private PeopleMapperClient peopleMapperClient;
    @RequestMapping(value = "/people/getPeopleList",method = RequestMethod.GET)
    public ResponseBean getRoleList(@RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "start",defaultValue = "1")Integer start,
                                    @RequestParam(value = "size",defaultValue = "3")Integer size){
        System.out.println("waddwawd"+peopleMapperClient.getPeopleList(name, start, size));
        return peopleMapperClient.getPeopleList(name, start, size);
    }
    @RequestMapping(value = "/people/delPeople",method = RequestMethod.POST)
    public ResponseBean delPeople(@RequestParam(value = "id")Integer id) {
        System.out.println("del----------------------"+id);
        return peopleMapperClient.delPeople(id);
    }
    @RequestMapping(value = "/people/getDepList",method = RequestMethod.GET)
    public ResponseBean getDepList() {
        return peopleMapperClient.getDepList();
    }
}
