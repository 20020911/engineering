package com.example.mapper;

import com.example.bean.ResponseBean;
import com.example.config.FeignConfig;
import com.example.entity.Department;
import com.example.fallback.PeopleMapperClientFallback;
import com.example.fallback.ProjectsMapperClient;
import com.example.vo.PeopleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(configuration = FeignConfig.class,value = "user-controller",fallback = PeopleMapperClientFallback.class)
public interface PeopleMapperClient {
    @RequestMapping(value = "/people/getPeopleList",method = RequestMethod.GET)
    public ResponseBean getPeopleList(@RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "start",defaultValue = "1")Integer start,
                                    @RequestParam(value = "size",defaultValue = "3")Integer size);
    @RequestMapping(value = "/people/delPeople",method = RequestMethod.POST)
    public ResponseBean delPeople(@RequestParam(value = "id")Integer id);
    @RequestMapping(value = "/people/getDepList",method = RequestMethod.GET)
    public ResponseBean getDepList() ;
}
