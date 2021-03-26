package com.riiyn.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riiyn.entity.Admin;
import com.riiyn.entity.User;
import com.riiyn.mapper.AdminMapper;
import com.riiyn.service.AdminService;
import com.riiyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable String username, @PathVariable String password, @PathVariable String type){
        Object o = null;
        switch (type){
            case "user":
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.eq("username", username).eq("password", password);
                o = userService.getOne(userQueryWrapper);
                break;
            case "admin":
                QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
                adminQueryWrapper.eq("username", username).eq("password", password);
                o = adminService.getOne(adminQueryWrapper);
                break;
        }
        return o;
    }
    
    
}
