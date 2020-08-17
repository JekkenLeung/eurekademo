package com.jekken.eureka.service.fallback;

import com.jekken.eureka.service.SayHelloService;
import org.springframework.stereotype.Component;

/**
 * Create by Jekken
 * 2020/8/16 12:59
 */
@Component
public class SayHelloFallBackService implements SayHelloService {
    @Override
    public String home() {
        return "error";
    }
}
