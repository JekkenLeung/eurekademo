package com.jekken.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Create by Jekken
 * 2020/8/16 13:25
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableDiscoveryClient
public class DashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class,args);
    }
}
