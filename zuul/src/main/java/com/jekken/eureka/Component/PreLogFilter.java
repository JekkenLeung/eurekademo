package com.jekken.eureka.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 前置过滤器
 * Create by Jekken
 * 2020/8/19 22:01
 */
@Component
public class PreLogFilter extends ZuulFilter {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContent = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContent.getRequest();
        String uri = request.getRequestURI();
        LOGGER.info(uri);
        return null;
    }
}
