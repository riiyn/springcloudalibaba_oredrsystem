package com.riiyn.feign;

import com.riiyn.entity.Menu;
import com.riiyn.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "menu", path = "menu")
public interface MenuFeign {
    
    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Integer id);
    
    @GetMapping("/getTypes")
    List<Type> getTypes();
    
    @PutMapping("/update")
    boolean update(@RequestBody Menu menu);
    
    @PostMapping("/save")
    boolean save(@RequestBody Menu menu);
    
    @GetMapping("/search/{param}")
    List<Menu> search(@PathVariable String param);
    
    @GetMapping("/getFlavors")
    List<String> getFlavors();
}
