package com.example.ucenter.config.shiro;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.druid.util.StringUtils;
import com.example.bean.ActiveUser;
import com.example.bean.JWTUser;
import com.example.config.jwt.JWTToken;
import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.ucenter.mapper.UsersMapper;
import com.example.ucenter.service.IUserService;
import com.example.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义用户认证授权
 */
@Service
public class UserRealm extends AuthorizingRealm {
    @Resource
    private IUserService userService;
    @Resource
    private UsersMapper usersMapper;



    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 当需要检测用户权限的时候执行当前方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if(activeUser.getUser().getType()==0){
            authorizationInfo.addStringPermission("*:*");
        }else{
            List<String> permissions = new ArrayList<>(activeUser.getPermissions());
            List<Role> roleList = activeUser.getRoles();
            if(!CollectionUtils.isEmpty(roleList)){
                 for(Role role : roleList){
                     authorizationInfo.addRole(role.getRoleName());
                 }
            }
            if(!CollectionUtils.isEmpty(permissions)){
                for(String permission : permissions){
                  if(permission!=null && !"".equals(permission)){
                        authorizationInfo.addStringPermission(permission);
                  }
                }
            }
            return authorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token  =(String) auth.getCredentials();
        String username = JWTUtils.getUsername(token);
        if(username==null){
            throw new  AuthenticationException("token错误,请重新输入");
        }
        User userBean = usersMapper.getUserByName(username);

//        if(userBean == null){
//            throw new AccountException("账号不存在");
//        }
//
//        if(!JWTUtils.verify(token,username,userBean.getPassword())){
//            throw new CredentialsException("密码错误！");
//        }
        if(userBean.getStatus()==0){
            throw new LockedAccountException("账号已被禁用！");
        }
        List<Role> roles = userService.findRolesByid(userBean.getId());

        List<Menu> menus = userService.fingMentsBuRoles(roles);

        Set<String> urls = new HashSet<>();
        Set<String> perms = new HashSet<>();
        if(!CollectionUtil.isEmpty(menus)){
            for(Menu menu : menus){
                String url =menu.getUrl();
                String per = menu.getPerms();

                if(menu.getType()=='0' && !StringUtils.isEmpty(url)){
                    urls.add(url);
                }
                if(menu.getType()=='1' && !StringUtils.isEmpty(per)){
                    perms.add(per);
                }
            }
        }

        //过滤出url和用户的权限
        ActiveUser activeUser = new ActiveUser();
        activeUser.setRoles(roles);
        activeUser.setMenus(menus);
        activeUser.setToken(token);
        activeUser.setUrls(urls);
        activeUser.setUser(userBean);
        activeUser.setPermissions(perms);
        return new SimpleAuthenticationInfo(activeUser,token,getName());
    }
}
