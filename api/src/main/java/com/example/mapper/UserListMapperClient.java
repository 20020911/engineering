package com.example.mapper;

import com.example.bean.ResponseBean;
import com.example.config.FeignConfig;
import com.example.entity.User_Role;
import com.example.fallback.UserListMapperClientFallback;
import com.example.fallback.UserMapperClientFallback;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( configuration = FeignConfig.class,value = "user-controller",fallback = UserListMapperClientFallback.class)
public interface UserListMapperClient {
    @RequestMapping(value = "/user/getUserList",method = RequestMethod.GET)
    ResponseBean getUserList(@RequestParam(value = "name",required = false) String name,
                             @RequestParam(value = "status",defaultValue = "0") Integer status,
                             @RequestParam(value = "start",defaultValue = "1")Integer start,
                             @RequestParam(value = "size",defaultValue = "3")Integer size);
    @RequestMapping(value = "/user/getRoleList",method = RequestMethod.GET)
    public ResponseBean getRoleList();
    @RequestMapping(value = "/user/updPwd",method = RequestMethod.POST)
    ResponseBean updPwd(@RequestParam("password")String password, @RequestParam("id")Integer id);
    @RequestMapping(value = "/user/updInfo",method = RequestMethod.POST)
    ResponseBean updInfo(@RequestParam("name")String name,@RequestParam("id")Integer id,@RequestParam("status")Integer status);
    @RequestMapping(value = "/user/getStatusList",method = RequestMethod.GET)
    ResponseBean getStatusList();
    @RequestMapping(value = "/user/getURList",method = RequestMethod.GET)
    public ResponseBean getURList(@RequestParam("id")Integer id);
    @RequestMapping(value = "/user/getUser",method = RequestMethod.GET)
    public ResponseBean getUser(@RequestParam(value = "id")Integer id,@RequestParam(value = "password")String password);
    @RequestMapping(value = "/user/addRole",method = RequestMethod.POST)
    public ResponseBean addRole(@RequestBody User_Role user_role);
    @RequestMapping(value = "/user/delRole",method = RequestMethod.POST)
    public ResponseBean delRole(@RequestBody User_Role user_role);
}
