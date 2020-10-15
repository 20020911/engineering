package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.Role;
import com.example.entity.Status;
import com.example.entity.User;
import com.example.entity.User_Role;
import com.example.service.UserListService;
import com.example.vo.UserListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserListController {
    @Resource
    private UserListService userListService;
    @RequestMapping(value = "/user/getUserList",method = RequestMethod.GET)
    public ResponseBean getUserList(@RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "status",defaultValue = "0") Integer status,
                                    @RequestParam(value = "start",defaultValue = "1")Integer start,
                                    @RequestParam(value = "size",defaultValue = "3")Integer size){
        try {
            List<UserListVo> list = userListService.getUserList(name,status,start,size);
            int count = userListService.getUserListCount(name,status);
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
    @RequestMapping(value = "/user/getStatusList",method = RequestMethod.GET)
    public ResponseBean getStatusList(){
        try {
            List<Status> list = userListService.getStatusList();
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
    @RequestMapping(value = "/user/getRoleList",method = RequestMethod.GET)
    public ResponseBean getRoleList(){
        try {
            List<Role> list = userListService.getRoleList();
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
    @RequestMapping(value = "/user/getURList",method = RequestMethod.GET)
    public ResponseBean getURList(@RequestParam("id")Integer id){
        try {
            List<User_Role> list = userListService.getURList(id);
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
    @RequestMapping(value = "/user/updPwd",method = RequestMethod.POST)
    public ResponseBean updPwd(@RequestParam(value = "id")Integer id,@RequestParam(value = "password")String password){
        try {
            int count= userListService.updPwd(password,id);
            if(count>0){
                return ResponseBean.success("修改成功");
            }else{
                return ResponseBean.success("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @RequestMapping(value = "/user/updInfo",method = RequestMethod.POST)
    public ResponseBean updInfo(@RequestParam(value = "id")Integer id,@RequestParam(value = "name")String name,@RequestParam("status")Integer status){
        try {
            int count= userListService.updInfo(name,id,status);
            if(count>0){
                return ResponseBean.success("修改成功");
            }else{
                return ResponseBean.success("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @RequestMapping(value = "/user/getUser",method = RequestMethod.GET)
    public ResponseBean getUser(@RequestParam(value = "id")Integer id,@RequestParam(value = "password")String password){
        try {
            User user= userListService.getUser(password,id);
            if(user!=null){
                return ResponseBean.success("密码正确");
            }else{
                return ResponseBean.success("密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @RequestMapping(value = "/user/addRole",method = RequestMethod.POST)
    public ResponseBean addRole(@RequestBody User_Role user_role){
        try {
            int count= userListService.addRole(user_role);
            if(count>=0){
                return ResponseBean.success("正确");
            }else{
                return ResponseBean.success("错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @RequestMapping(value = "/user/delRole",method = RequestMethod.POST)
    public ResponseBean delRole(@RequestBody User_Role user_role){
        try {
            int count= userListService.delRole(user_role);
            if(count>=0){
                return ResponseBean.success("正确");
            }else{
                return ResponseBean.success("错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
}
