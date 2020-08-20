package com.jekken.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Create by Jekken
 * 2020/8/20 21:24
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AdminClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminClientApplication.class,args);
    }
}
