package com.example.ucenter.controller;

import com.example.bean.ResponseBean;
import com.example.ucenter.service.IUserService;
import com.example.utils.JWTUtils;
import com.example.utils.TokenUtil;
import com.example.vo.MenuNodeVo;
import com.example.vo.UserInfoVo;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/ucenter/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    @PostMapping("/login")
    public ResponseBean login(@NotBlank(message = "用户名必填") String name,@NotBlank(message = "密码必填")  String password, HttpServletRequest request){
        //System.out.println(name);
        String token  = iUserService.login(name,password);
        TokenUtil.setToken(token);
      return ResponseBean.success(token);
    }
    @ApiOperation(value="用户信息",notes = "用户登录信息")
    @RequestMapping("/info")
    public ResponseBean info(){
        UserInfoVo userInfoVo = iUserService.info();
        return ResponseBean.success(userInfoVo);
    }
    @ApiOperation(value="加载用户菜单",notes="用户登录后，根据角色加载菜单树")
    @GetMapping("/findMenu")
    public ResponseBean findMenu(){
        List<MenuNodeVo> menuNodeVoList = iUserService.findMenu();
        return ResponseBean.success(menuNodeVoList);
    }
}
