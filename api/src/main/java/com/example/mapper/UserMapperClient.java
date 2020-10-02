package com.example.mapper;

import com.example.fallback.UserMapperClientFallback;
import com.example.pojo.pojo.User;
import com.example.pojo.util.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eureka-user-controller",fallback = UserMapperClientFallback.class)
public interface UserMapperClient {
    @PostMapping("/user/hello")
    public ResponseData<Object> getUserByUser(@RequestBody User user);
}
