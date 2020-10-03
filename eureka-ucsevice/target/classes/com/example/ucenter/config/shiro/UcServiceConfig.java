package com.example.ucenter.config.shiro;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.*.mapper")
public class UcServiceConfig {
}
