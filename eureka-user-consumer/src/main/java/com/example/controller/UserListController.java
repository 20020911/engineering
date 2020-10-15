package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.User_Role;
import com.example.mapper.UserListMapperClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/call/user")
public class UserListController {
    @Autowired
    private UserListMapperClient userListMapperClient;
    @RequestMapping(value = "/getUserlist",method = RequestMethod.GET)
    public ResponseBean getUserList(@RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "status",defaultValue = "0") Integer status,
                                    @RequestParam(value = "start",defaultValue = "1")Integer start,
                                    @RequestParam(value = "size",defaultValue = "3")Integer size){
        return userListMapperClient.getUserList(name, status, start, size);
    }
    @RequestMapping(value = "/getStatusList",method = RequestMethod.GET)
    public ResponseBean getStatusList(){
        //System.out.println("---------------------------"+userListMapperClient.getStatusList().toString());
        return userListMapperClient.getStatusList();
    }
    @RequestMapping(value = "/updPwd",method = RequestMethod.POST)
    public ResponseBean updPwd(@RequestParam("password")String password,@RequestParam("password2")String password2, @RequestParam("id")Integer id){
        System.out.println("password:"+password+"------password2"+password2+"-----id"+id);
        System.out.println(userListMapperClient.getUser(id,password).getMessage()+"getuser");
        if(!userListMapperClient.getUser(id,password).getMessage().equals("密码错误")){
            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyy");
            return userListMapperClient.updPwd(password2,id);
        }
        System.out.println("nnnnnnnnnnnnnnnnnnnnnn");
        return ResponseBean.error("用户密码不正确");
    }
    @RequestMapping(value = "/getRoleList",method = RequestMethod.GET)
    public ResponseBean getRoleList(){
        return userListMapperClient.getRoleList();
    }
    @RequestMapping(value = "/getURList",method = RequestMethod.GET)
    public ResponseBean getURList(@RequestParam("uid")Integer uid){
        //System.out.println("uid----------------"+uid);
        return userListMapperClient.getURList(uid);
    }
    @RequestMapping(value = "/updInfo",method = RequestMethod.POST)
    public ResponseBean updInfo(@RequestParam("name")String name,@RequestParam("id")Integer id,@RequestParam("status")Integer status,@RequestParam("role[]")Integer[] role){
        //System.out.println("role----"+role);
        for (Integer integer : role) {
            System.out.println("role"+integer.intValue());
        }

        //List<User_Role> list =(List<User_Role>)userListMapperClient.getURList(id).getData().toString();
//        for (User_Role ur :list){
//            System.out.println("list"+ur.getRoleId());
//        }
        System.out.println(userListMapperClient.getURList(id).getData().toString().length());
        System.out.println("id------"+id);
        System.out.println("name----"+name);
        System.out.println("status--"+status);
        return ResponseBean.success("wadawd");//userListMapperClient.updInfo(name,id,status);
    }

}
