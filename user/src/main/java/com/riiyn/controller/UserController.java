package com.riiyn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riiyn.entity.User;
import com.riiyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/search/{param}/{current}/{size}")
    public Page<User> search(@PathVariable String param, @PathVariable Integer current, @PathVariable Integer size){
        final QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", param).or().like("nickname", param).or().like("address", param);
        return userService.page(new Page<>(current, size), queryWrapper);
    }
    
    @GetMapping("/findAll/{current}/{size}")
    public Page<User> findAll(@PathVariable Integer current, @PathVariable Integer size){
        return userService.page(new Page<>(current, size));
    }
    
    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        if (null != user){
            if (null == user.getRegisterdate())
                user.setRegisterdate(new Date());
        }
        return userService.save(user);
    }
    
    @PutMapping("/update")
    public boolean update(@RequestBody User user){
        return userService.updateById(user);
    }
    
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }
}
