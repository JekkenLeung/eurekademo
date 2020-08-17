package com.jekken.eureka.controller;

import com.jekken.eureka.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Jekken
 * 2020/8/15 21:48
 */
@RestController
public class FeignController {

    @Autowired
    private SayHelloService sayHelloService;

    @GetMapping("/say")
    private String Say(){
        return sayHelloService.home();
    }
}
