package com.riiyn.controller;

import com.riiyn.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    private AccountFeign accountFeign;
    
    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable String username, @PathVariable String password, @PathVariable String type){
        return accountFeign.login(username, password, type);
    }
}
