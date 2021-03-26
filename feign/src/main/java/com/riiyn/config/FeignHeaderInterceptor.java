package com.riiyn.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

//@Configuration
public class FeignHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-Type", "application/json; charset=UTF-8");
    }
}
