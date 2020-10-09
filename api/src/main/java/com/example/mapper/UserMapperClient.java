package com.example.mapper;

import com.example.bean.ResponseBean;
import com.example.entity.User;
import com.example.fallback.UserMapperClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "user-controller",fallback = UserMapperClientFallback.class)
public interface UserMapperClient {
    @PostMapping("/user/addUser")
    public ResponseBean addUser(@RequestBody User user);
    @PostMapping("/user/remoteUser")
    public ResponseBean remoteUser(@RequestParam String name);
    @PostMapping("/user/remoteUsers")
    public ResponseBean remoteUsers(@RequestParam String phone);

}
