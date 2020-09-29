package com.example.eurekaprojectcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaProjectControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProjectControllerApplication.class, args);
    }

}
