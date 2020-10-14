package com.example.controller;

import com.example.bean.ResponseBean;
import com.example.entity.Menu;
import com.example.entity.User;
import com.example.mapper.UserMapperClient;
import com.example.vo.RoleMenuListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sumer")
public class UsersController {
    @Autowired
    private UserMapperClient userMapperClient;
    @PostMapping("/addUser")
    public ResponseBean addUser( User user){
       return  userMapperClient.addUser(user);
    }
    @PostMapping("/remoteUser")
    public ResponseBean remoteUser(@RequestParam String name){
        return userMapperClient.remoteUser(name);
    }
    @PostMapping("/remoteUsers")
    public ResponseBean remoteUsers(@RequestParam String phone){
        return userMapperClient.remoteUsers(phone);
    }
    @PostMapping("/menuList")
    public ResponseBean menuList(@RequestParam String menuName,@RequestParam String url){
        return userMapperClient.menuList(menuName,url);
    }
    @GetMapping("/init")
    public ResponseBean init(){
        return userMapperClient.init();
    }
    @GetMapping("/menu/countMenuList/{id}")
    public ResponseBean countMenuList(@PathVariable("id") int id){
        return userMapperClient.countMenuList(id);
    }
    @DeleteMapping("/menu/delMenuById/{id}")
    public ResponseBean delMenuById(@PathVariable("id") int id){
        return userMapperClient.delMenuById(id);
    }
    @GetMapping("/menu/showMenuById/{id}")
    public ResponseBean showMenuById(@PathVariable("id") int id){
        return userMapperClient.showMenuById(id);
    }
    @GetMapping("/menu/menuListById/{id}")
    public ResponseBean menuListById(@PathVariable("id") int id){
        return userMapperClient.menuListById(id);
    }
    @PutMapping("/menu/updateMenuByMenu")
    public ResponseBean updateMenuByMenu(Menu menu){
        return userMapperClient.updateMenuByMenu(menu);
    }
    @GetMapping("/menu/menuLists")
    public ResponseBean menuLists(){
        return userMapperClient.menuLists();
    }
    @PostMapping("/menu/addMenu")
    public ResponseBean addMenu( Menu menu){
        return userMapperClient.addMenu(menu);
    }
    @PostMapping("/role/roleList")
    public ResponseBean roleList(@RequestParam("roleName") String roleName,@RequestParam(value="status",defaultValue = "-1",required = false) int status
            ,@RequestParam("page") int page,@RequestParam("pageSize") int pageSize){
        return userMapperClient.roleList(roleName,status,page,pageSize);
    }
    @PostMapping("/role/addRole")
    public ResponseBean addRole(RoleMenuListVo role){
        return userMapperClient.addRole(role);
    }
    @DeleteMapping("/role/delRole/{id}")
    public ResponseBean delRole(@PathVariable("id") int id){
        return userMapperClient.delRole(id);
    }
    @DeleteMapping("/role/delUserRole")
    public ResponseBean delUserRole(@RequestParam("roleId")int roleId,@RequestParam("userId")int userId){
        return userMapperClient.delUserRole(roleId,userId);
    }
    @PostMapping("/role/userRoleList")
    public ResponseBean userRoleList(@RequestParam int roleId,@RequestParam String name,@RequestParam String phone,@RequestParam int page,@RequestParam int pageSize){
        return userMapperClient.userRoleList(roleId,name,phone,page,pageSize);
    }

}
