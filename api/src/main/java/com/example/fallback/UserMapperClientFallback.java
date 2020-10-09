package com.example.fallback;

import com.example.bean.ResponseBean;
import com.example.entity.User;
import com.example.mapper.UserMapperClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

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
}
