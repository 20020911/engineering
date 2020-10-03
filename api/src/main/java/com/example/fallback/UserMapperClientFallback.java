package com.example.fallback;

import com.example.mapper.UserMapperClient;
import com.example.pojo.pojo.User;
import com.example.pojo.util.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UserMapperClientFallback implements UserMapperClient {
    @Override
    public ResponseData<Object> getUserByUser(@RequestBody  User user) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(600);
        responseData.setMsg("访问出错");
        responseData.setData(null);
        return responseData;
    }
}
