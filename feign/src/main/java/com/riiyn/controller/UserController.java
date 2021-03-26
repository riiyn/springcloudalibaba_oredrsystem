package com.riiyn.controller;

import com.riiyn.entity.User;
import com.riiyn.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFeign userFeign;
    
    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        return userFeign.save(user);
    }
    
    @PutMapping("/update")
    public boolean update(@RequestBody User user){
        return userFeign.update(user);
    }
    
    @DeleteMapping("/delete/{id}")
    public boolean del(@PathVariable Integer id){
        return userFeign.delete(id);
    }
}
