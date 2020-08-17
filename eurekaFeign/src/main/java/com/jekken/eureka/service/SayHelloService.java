package com.jekken.eureka.service;

import com.jekken.eureka.service.fallback.SayHelloFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create by Jekken
 * 2020/8/15 17:43
 */
@FeignClient(value = "SayHello",fallback = SayHelloFallBackService.class)
@Service
public interface SayHelloService {

    @GetMapping("/hello")
    String home();


}
