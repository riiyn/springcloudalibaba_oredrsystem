package com.riiyn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "account", path = "account")
public interface AccountFeign {
    
    @GetMapping("/login/{username}/{password}/{type}")
    Object login(@PathVariable String username, @PathVariable String password, @PathVariable String type);
}
