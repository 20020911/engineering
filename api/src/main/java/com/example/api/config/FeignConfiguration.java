package com.example.api.config;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
//    @Bean
//    Logger.Level feignLoggerLevel(){
//        return Logger.Level.FULL;
//    }
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("admin","admin");
    }
    @Bean
    public Request.Options options(){
        return new Request.Options(50000,100000);

    }
    /**
     * Feign默认契约配置
     */
//    @Bean
//    public Contract feignContract(){
//        return new Contract.Default();
//    }
}
