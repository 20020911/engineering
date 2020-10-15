package com.example.mapper;

import com.example.bean.ResponseBean;
import com.example.config.FeignConfig;
import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.fallback.UserMapperClientFallback;
import com.example.vo.MenuNodeVo;
import com.example.vo.RoleMenuListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient( configuration = FeignConfig.class,value = "user-controller",fallback = UserMapperClientFallback.class)
public interface UserMapperClient {
    @PostMapping("/user/addUser")
    public ResponseBean addUser(@RequestBody User user);
    @PostMapping("/user/remoteUser")
    public ResponseBean remoteUser(@RequestParam String name);
    @PostMapping("/user/remoteUsers")
    public ResponseBean remoteUsers(@RequestParam String phone);
    @GetMapping("/user/init")
    public ResponseBean init();
    @PostMapping("/menu/menuList")
    public ResponseBean menuList(@RequestParam String menuName,@RequestParam String url);
    @GetMapping("/menu/countMenuList/{id}")
    public ResponseBean countMenuList(@PathVariable("id") int id);
    @DeleteMapping("/menu/delMenuById/{id}")
    public ResponseBean delMenuById(@PathVariable("id") int id);
    @GetMapping("/menu/showMenuById/{id}")
    public ResponseBean showMenuById(@PathVariable("id") int id);
    @GetMapping("/menu/menuListById/{id}")
    public ResponseBean menuListById(@PathVariable("id") int id);
    @PutMapping("/menu/updateMenuByMenu")
    public ResponseBean updateMenuByMenu(@RequestBody Menu menu);
    @GetMapping("/menu/menuLists")
    public ResponseBean menuLists();
    @PostMapping("/menu/addMenu")
    public ResponseBean addMenu(@RequestBody Menu menu);
    @PostMapping("/role/roleList")
    public ResponseBean roleList(@RequestParam("roleName") String roleName,@RequestParam("status") int status
            ,@RequestParam("page") int page,@RequestParam("pageSize") int pageSize);
    @PostMapping("/role/addRole")
    public ResponseBean addRole(@RequestBody RoleMenuListVo role);
    @DeleteMapping("/role/delRole/{id}")
    public ResponseBean delRole(@PathVariable("id") int id);
    @DeleteMapping("/role/delUserRole")
    public ResponseBean delUserRole(@RequestParam("roleId")int roleId,@RequestParam("userId")int userId);
    @PostMapping("/role/userRoleList")
    public ResponseBean userRoleList(@RequestParam int roleId,@RequestParam String name,@RequestParam String phone,@RequestParam int page,@RequestParam int pageSize);
}
