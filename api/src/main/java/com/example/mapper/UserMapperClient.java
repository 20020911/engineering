package com.example.mapper;

import com.example.bean.ResponseBean;
import com.example.entity.User;
import com.example.fallback.UserMapperClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eureka-ucsevice",fallback = UserMapperClientFallback.class)
public interface UserMapperClient {
    @PostMapping("/user/hello")
    public ResponseBean getUserByUser(@RequestBody User user);
}
