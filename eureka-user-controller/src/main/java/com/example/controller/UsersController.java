package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.User;
import com.example.service.UsersService;
import com.example.utils.TokenUtil;
import com.example.vo.MenuListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UsersController {
    @Resource
    private UsersService usersService;
    @PostMapping("/menu/menuList")
    public ResponseBean menuList(@RequestParam String menuName,@RequestParam int status){
        try{
            List<MenuListVo> menuListVoList = usersService.menuList(menuName,status);
            int count = usersService.menuListCount(menuName,status);
            if(!CollectionUtils.isEmpty(menuListVoList)){
                ResponseBean responseBean = new ResponseBean();
                responseBean.setCount(count);
                responseBean.setData(menuListVoList);
                responseBean.setMessage("ok");
                responseBean.setCode(0);
                return responseBean;
            }else{
                return ResponseBean.success("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }
    @GetMapping("/menu/countMenuList/{id}")
    public ResponseBean countMenuList(@PathVariable("id") int id){
        return ResponseBean.success(usersService.coutMenuListById(id));
    }
    @PostMapping("/user/addUser")
    public ResponseBean addUser(@RequestBody User user){
        try{
            int count = usersService.addUser(user);
            if(count>0){
                return ResponseBean.success("注册成功！");
            }else{
                return ResponseBean.error("注册失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,e.getMessage());
        }
    }

    @PostMapping("/user/remoteUser")
    public ResponseBean remoteUser(@RequestParam String name){
        try{
            User user = usersService.remoteUser(name);
            if(user == null){
                return ResponseBean.success("可以使用");
            }else{
                return ResponseBean.error(4001,"名称重复");
            }
        }catch (Exception e){
            return ResponseBean.error(500,"发生异常");
        }
    }
    @GetMapping("/user/init")
    public ResponseBean init(){
        return ResponseBean.success("sas");
    }
    @PostMapping("/user/remoteUsers")
    public ResponseBean remoteUsers(@RequestParam String phone){
        try{
            User user = usersService.remoteUsers(phone);
            if(user == null){
                return ResponseBean.success("可以使用");
            }else{
                return ResponseBean.error(4001,"手机号重复");
            }
        }catch (Exception e){
            return ResponseBean.error(500,"发生异常");
        }
    }

}
