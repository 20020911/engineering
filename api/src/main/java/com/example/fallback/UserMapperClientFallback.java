package com.example.fallback;

import com.example.bean.ResponseBean;
import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.mapper.UserMapperClient;
import com.example.vo.MenuNodeVo;
import com.example.vo.RoleMenuListVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class UserMapperClientFallback implements UserMapperClient {

    @Override
    public ResponseBean addUser(User user) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean remoteUser(String name) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean remoteUsers(String phone) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean init() {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean menuList(String menuName, String url) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean countMenuList(int id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean delMenuById(int id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean showMenuById(int id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean menuListById(int id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean updateMenuByMenu(Menu menu) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean menuLists() {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean addMenu(Menu menu) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean roleList(String roleName, int status, int page, int pageSize) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean addRole(RoleMenuListVo role) {
        return ResponseBean.error(600,"访问错误");
    }

    @Override
    public ResponseBean delRole(int id) {
        return ResponseBean.error(600,"访问错误");
    }

    @Override
    public ResponseBean delUserRole(int roleId, int userId) {
        return ResponseBean.error(600,"访问错误");
    }

    @Override
    public ResponseBean userRoleList(int roleId, String name, String phone, int page, int pageSize) {
        return ResponseBean.error(600,"访问错误");
    }


}
