package com.riiyn.feign;

import com.riiyn.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user", path = "user")
public interface UserFeign {
    
    @PostMapping("/save")
    boolean save(@RequestBody User user);
    
    @PutMapping("/update")
    boolean update(@RequestBody User user);
    
    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Integer id);
}
