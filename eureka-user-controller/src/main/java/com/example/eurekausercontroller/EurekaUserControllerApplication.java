package com.example.eurekausercontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaUserControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaUserControllerApplication.class, args);
    }

}
