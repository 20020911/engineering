package com.example.ucenter.controller;

import com.example.bean.ResponseBean;
import com.example.ucenter.service.IUserService;
import com.example.utils.AddressUtil;
import com.example.utils.TokenUtil;
import com.example.vo.MenuNodeVo;
import com.example.vo.UserInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/ucenter/user")
public class UserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/login")
    public ResponseBean login(@NotBlank(message = "用户名必填") String name,@NotBlank(message = "密码必填")  String password, HttpServletRequest request){
        //System.out.println(name);
        String token  = iUserService.login(name,password);
        String ip = AddressUtil.getLocalIpAddr();
        System.out.println(ip);
        try{
            assert ip != null;
            redisTemplate.opsForHash().put("token:"+ip,ip,token);
            redisTemplate.expire("token:"+ip,43200, TimeUnit.SECONDS);
        }catch (Exception e){
            return ResponseBean.error(e.getMessage());
        }
        TokenUtil.setToken(token);

      return ResponseBean.success(token);
    }
    @GetMapping("/token")
    public String token(){
        try{
            String ip = AddressUtil.getLocalIpAddr();
            return (String)redisTemplate.opsForHash().get("token:"+ip,ip);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation(value="用户信息",notes = "用户登录信息")
    @GetMapping("/info")
    public ResponseBean info(){
        UserInfoVo userInfoVo = iUserService.info();
        return ResponseBean.success(userInfoVo);
    }
    //@ApiOperation(value="加载用户菜单",notes="用户登录后，根据角色加载菜单树")
    @GetMapping("/findMenu")
    public ResponseBean findMenu(HttpServletRequest request){
        List<MenuNodeVo> menuNodeVoList = iUserService.findMenu();
        System.out.println(menuNodeVoList);
        return ResponseBean.success(menuNodeVoList);
    }
}
