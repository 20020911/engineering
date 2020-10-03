package com.example.ucenter.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.ServiceException;
import com.example.bean.ActiveUser;
import com.example.config.jwt.JWTToken;
import com.example.entity.*;
import com.example.enums.UserTypeEnum;
import com.example.ucenter.config.shiro.UserRealm;
import com.example.ucenter.converter.MenuConverter;
import com.example.ucenter.mapper.*;
import com.example.utils.JWTUtils;
import com.example.utils.MD5Utils;
import com.example.utils.MenuTreeBuilder;
import com.example.vo.MenuNodeVo;
import com.example.vo.UserInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IUserServiceimpl implements IUserService{
//    @Autowired
//    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private User_RolesMapper userRoleMapper;
    @Resource
    private UsersMapper usersMapper;

    @Autowired
    private Role_MenuMapper roleMenuMapper;



    @Override
    public User findUserByName(String name) {
        return usersMapper.selectOne(name);
    }

    @Override
    public List<Role> findRolesByid(int id) {
       User dbUser = usersMapper.selectByPrimaryKey(id);
       if(dbUser == null){
           throw new ServiceException("该用户不存在！");
       }
        List<Role> roles = new ArrayList<>();
        User_Role userRole= new User_Role();
        userRole.setUserId(dbUser.getId());

        List<User_Role> userRoleList = userRoleMapper.getUserRoleListByRole(id);
        List<Integer> rids = new ArrayList<>();
        if(!CollectionUtil.isEmpty(userRoleList)){
            for(User_Role ur:userRoleList){
                rids.add(ur.getRoleId());
            }
            if(!CollectionUtil.isEmpty(rids)){
                for(int rid : rids){
                    Role role =roleMapper.findRolesByid(rid);
                    if(role!=null){
                        roles.add(role);
                    }
                }
            }
        }

        return roles;
    }

    @Override
    public List<Menu> fingMentsBuRoles(List<Role> roles) {

        List<Menu> menus = new ArrayList<>();
        if(!CollectionUtil.isEmpty(roles)){
            Set<Integer> menuIds = new HashSet<>();
            List<Role_Menu> roleMenus;
            for(Role role : roles){
                roleMenus =roleMenuMapper.selectByExample(role.getId());
                if(!CollectionUtil.isEmpty(roleMenus)){
                    for(Role_Menu roleMenu : roleMenus){
                        menuIds.add(roleMenu.getMenuId());
                    }
                }
            }
            if(!CollectionUtil.isEmpty(menuIds)){
                for(Integer id : menuIds){
                    Menu menu =menuMapper.selectByPrimaryKey(id);
                    if(menu!=null){
                        menus.add(menu);
                    }

                }
            }
        }
        return menus;
    }

    @Override
    public String login(String name, String password) {
        String token="";
        User user  = findUserByName(name);
        if(user!=null){
            if(usersMapper.getUserByNameAndPwd(name,password) == null){
                throw new CredentialsException("密码错误！");
            }
            String salt = user.getSalt();

            String target = MD5Utils.md5Encryption(password,salt);
            token = JWTUtils.sign(name,target);

            JWTToken jwtToken  = new JWTToken(token);

            try{

                SecurityUtils.getSubject().login(jwtToken);

//                ActiveUser activeUser =(ActiveUser) SecurityUtils.getSubject().getPrincipal();
//                UserInfoVo userInfoVo  = new UserInfoVo();
//                System.out.println(activeUser.getUser().getName());
//                System.out.println(activeUser.getUrls());
//                //System.out.println(activeUser.getRoles());
//                System.out.println(activeUser.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()));

            }catch (AuthenticationException e){
                throw new ServiceException(e.getMessage());
            }
        }else{
            throw new AccountException("账号不存在");
        }
        return token;
    }

    @Override
    public UserInfoVo info() {
        ActiveUser activeUser =(ActiveUser) SecurityUtils.getSubject().getPrincipal();
        UserInfoVo userInfoVo  = new UserInfoVo();
        userInfoVo.setUsername(activeUser.getUser().getName());
        userInfoVo.setUrls(activeUser.getUrls());
        userInfoVo.setMenus(activeUser.getMenus());
        //System.out.println(activeUser.getRoles());
        userInfoVo.setRoles(activeUser.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()));
        return userInfoVo;
    }

    @Override
    public List<MenuNodeVo> findMenu() {
        List<Menu> menus = null;
        List<MenuNodeVo> menuNodeVos = new ArrayList<>();
        ActiveUser activeUser = (ActiveUser)SecurityUtils.getSubject().getPrincipal();
        if(activeUser.getUser().getType()== UserTypeEnum.SYSTEM_ADMIN.getTypeCode()){
            menus = menuMapper.getMenuAll();
        }else{
            menus=activeUser.getMenus();
        }
        if(!CollectionUtil.isEmpty(menus)){
            menuNodeVos= MenuConverter.converterToMenuNodeVo(menus);
        }
        return MenuTreeBuilder.build(menuNodeVos);
    }
}
