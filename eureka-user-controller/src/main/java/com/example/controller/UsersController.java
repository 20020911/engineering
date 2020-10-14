package com.example.controller;

import com.example.bean.ActiveUser;
import com.example.bean.ResponseBean;
import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.UsersService;
import com.example.utils.TokenUtil;
import com.example.vo.MenuListVo;
import com.example.vo.MenuNodeVo;
import com.example.vo.RoleMenuListVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {
    @Resource
    private UsersService usersService;
    @PostMapping("/menu/menuList")
    public ResponseBean menuList(@RequestParam String menuName,@RequestParam String url){
        try{
            List<MenuListVo> menuListVoList = usersService.menuList(menuName,url);
            int count = usersService.menuListCount(menuName,url);
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
    @PostMapping("/role/userRoleList")
    public ResponseBean userRoleList(@RequestParam int roleId,@RequestParam String name
            ,@RequestParam String phone,@RequestParam int page,@RequestParam int pageSize){
        try{
            page = (page-1)*pageSize;
            List<User> users = usersService.getUserListByRoleId(roleId,name,phone,page,pageSize);
            int count = usersService.countUserListByRoleId(roleId,name,phone);
            return ResponseBean.success(users,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }
    }
    @DeleteMapping("/role/delUserRole")
    public ResponseBean delUserRole(@RequestParam("roleId")int roleId,@RequestParam("userId")int userId){
        try{
            int count = usersService.deleteUserRoleByUserIdAndRoleId(roleId,userId);
            if(count>0){
                return ResponseBean.success("删除成功！");
            }else{
                return ResponseBean.error("删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("发生异常！");
        }
    }
    @DeleteMapping("/role/delRole/{id}")
    public ResponseBean delRole(@PathVariable("id") int id){
        try{
            int count = usersService.deleteRoleById(id);
            int count2 = usersService.deleteRoleMenuById(id);
            if(count>0){
                return ResponseBean.success("删除成功!");
            }else{
                return ResponseBean.success("删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }
    }
    @PostMapping("/role/addRole")
    public ResponseBean addRole(@RequestBody RoleMenuListVo role){
        try{

        Role roles = new Role();
        roles.setRemark(role.getRemark());
        roles.setRoleName(role.getRoleName());
        roles.setStatus(role.getStatus());
        ActiveUser activeUser = (ActiveUser)SecurityUtils.getSubject().getPrincipal();
        roles.setCreatedBy(activeUser.getUser().getId());

        int count = usersService.addRole(roles);
        for(int i =0 ;i<role.getMenuId().size();i++){
            String menuId = role.getMenuId().get(i);
            if(i==0){
                usersService.addRole_Menu(roles.getId(),Integer.parseInt(menuId.substring(2,menuId.length()-1)));
                continue;
            }
            if(i==role.getMenuId().size()-1){
                usersService.addRole_Menu(roles.getId(),Integer.parseInt(menuId.substring(1,menuId.length()-2)));
                continue;
            }
            usersService.addRole_Menu(roles.getId(),Integer.parseInt(menuId.substring(1,menuId.length()-1)));
        }

        if(count>0){
            return ResponseBean.success("新增信息成功！");
        }else{
            return ResponseBean.error("新增信息失败！");
        }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }
    }
    @PostMapping("/role/roleList")
    public ResponseBean roleList(@RequestParam("roleName") String roleName,@RequestParam("status") int status
            ,@RequestParam("page") int page,@RequestParam("pageSize") int pageSize){
        try{
            page = (page-1)*pageSize;
            int count = usersService.countRoleListByNameAndStatus(roleName,status);
            List<Role> roles = usersService.getRoleListByNameAndStatus(roleName,status,page,pageSize);
            return ResponseBean.success(roles,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }
    }
    @PostMapping("/menu/addMenu")
    public ResponseBean addMenu(@RequestBody Menu menu){
        try{
            ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            int createdBy = activeUser.getUser().getId();
            menu.setCreatedBy(createdBy);
            int count = usersService.addMenyByMenu(menu);
            if(count>0){
                return ResponseBean.success("新增菜单成功！");
            }else{
                return ResponseBean.error(400,"新增菜单失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseBean.error(500,"发生异常！");
        }
    }
    @GetMapping("/menu/menuLists")
    public ResponseBean menuLists(){
        try{
            List<Menu> menus = usersService.getMenuList();
            return ResponseBean.success(menus);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }
    }
    @PutMapping("/menu/updateMenuByMenu")
    public ResponseBean updateMenuByMenu(@RequestBody Menu menu){
        try{
            ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            int updatedBy = activeUser.getUser().getId();
            menu.setUpdatedBy(updatedBy);
            System.out.println(menu);
            int count = usersService.updateMenuByMenu(menu);
            if(count>0){
                return ResponseBean.success("修改成功！");
            }else{
                return ResponseBean.error("修改失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }


    }
    @GetMapping("/menu/menuListById/{id}")
    public ResponseBean menuListById(@PathVariable("id") int id) {
        try {
            List<Menu> menus = usersService.getMenuList();
            List<Menu> menus1 = usersService.getMenuListById(id);
            List<Menu> menus3 = new ArrayList<>();
            while (true) {
                menus3 = new ArrayList<>();
                for (Menu menu : menus1) {

                    List<Menu> menus2 = usersService.getMenuListById(menu.getId());
                    if (!CollectionUtils.isEmpty(menus2)) {
                        for (Menu menu1 : menus2) {
                            menus3.add(menu1);
                        }
                    }
                    menus.remove(menu);
                }
                if (menus3.size() > 0) {
                    menus1 = menus3;
                } else {
                    break;
                }

            }
            return ResponseBean.success(menus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error(500, "发生异常！");
        }

    }
    @GetMapping("/menu/showMenuById/{id}")
    public ResponseBean showMenuById(@PathVariable("id") int id){
        try{
            return ResponseBean.success(usersService.getMenuById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }
    }
    @DeleteMapping("/menu/delMenuById/{id}")
    public ResponseBean delMenuById(@PathVariable("id") int id){
        try{
            int count = usersService.deleteMenuById(id);
            if(count>0){
                return ResponseBean.success("删除成功");
            }else{
                return ResponseBean.error("删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }
    }
    @GetMapping("/menu/countMenuList/{id}")
    public ResponseBean countMenuList(@PathVariable("id") int id){
        try{
            return ResponseBean.success(usersService.coutMenuListById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(500,"发生异常！");
        }

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
