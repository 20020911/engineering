package com.example.fallback;

import com.example.bean.ResponseBean;
import com.example.entity.User;
import com.example.mapper.UserMapperClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UserMapperClientFallback implements UserMapperClient {
    @Override
    public ResponseBean getUserByUser(@RequestBody User user) {
        ResponseBean responseData = new ResponseBean();
        responseData.setCode(600);
        responseData.setMessage("访问出错");
        responseData.setData(null);
        return responseData;
    }
}
