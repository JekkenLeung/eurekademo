package com.jekken.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Jekken
 * 2020/8/15 16:06
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
public class EurekaClientApplication {
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class,args);
    }

    @GetMapping("/hello")
    public String home() {
        return port+":Hello world";
    }

}
