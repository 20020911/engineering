package org.example.mapper;


import com.example.pojo.pojo.User;
import com.example.pojo.util.ResponseData;
import org.example.mapper.UserMapperClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UserMapperClientFallback implements UserMapperClient {
    @Override
    public ResponseData<Object> getUserByUser(User user) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(600);
        responseData.setMsg("访问出错");
        responseData.setData(null);
        return responseData;
    }
}
