package com.riiyn.controller;

import com.riiyn.entity.Menu;
import com.riiyn.entity.Type;
import com.riiyn.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    
    @Autowired
    private MenuFeign menuFeign;
    
    @PutMapping("/update")
    public boolean update(@RequestBody Menu menu) {
        return menuFeign.update(menu);
    }
    
    @GetMapping("/getTypes")
    public List<Type> getTypes() {
        return menuFeign.getTypes();
    }
    
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return menuFeign.delete(id);
    }
    
    @GetMapping("/search/{param}")
    public List<Menu> search(@PathVariable String param) {
        return menuFeign.search(param);
    }
    
    @GetMapping("/getFlavors")
    public List<String> getFlavors() {
        return menuFeign.getFlavors();
    }
    
    @PostMapping("/save")
    public boolean save(@RequestBody Menu menu) {
        return menuFeign.save(menu);
    }
}
