package com.example.fallback;

import com.example.bean.ResponseBean;
import com.example.entity.User_Role;
import com.example.mapper.UserListMapperClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class UserListMapperClientFallback implements UserListMapperClient {

    @Override
    public ResponseBean getUserList(@RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "status",defaultValue = "0") Integer status,
                                    @RequestParam(value = "start",defaultValue = "1")Integer start,
                                    @RequestParam(value = "size",defaultValue = "3")Integer size) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean getRoleList() {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean updPwd(String password, Integer id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean updInfo(String name, Integer id, Integer status) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean getStatusList() {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean getURList(Integer id) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean getUser(Integer id, String password) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean addRole(User_Role user_role) {
        return ResponseBean.error(600,"访问出错");
    }

    @Override
    public ResponseBean delRole(User_Role user_role) {
        return ResponseBean.error(600,"访问出错");
    }
}
