package com.jekken.eureka.Component;

import org.springframework.util.AntPathMatcher;

/**
 * Create by Jekken
 * 2020/8/20 23:29
 */
public class PathUtil {

    private static AntPathMatcher matcher = new AntPathMatcher();

    public static boolean isPathMatch(String pattern, String path) {
        return matcher.match(pattern, path);
    }
}
