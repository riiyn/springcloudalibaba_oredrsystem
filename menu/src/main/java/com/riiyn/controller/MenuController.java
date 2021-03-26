package com.riiyn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riiyn.entity.Menu;
import com.riiyn.entity.Type;
import com.riiyn.service.MenuService;
import com.riiyn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    
    @Autowired
    private MenuService menuService;
    @Autowired
    private TypeService typeService;
    
    @GetMapping("/findAll/{current}/{size}")
    public IPage<Menu> findAll(@PathVariable Long current, @PathVariable Long size) {
        return menuService.getPageVo(new Page<>(current, size));
    }
    
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return menuService.removeById(id);
    }
    
    @GetMapping("/getTypes")
    public List<Type> getTypes() {
        return typeService.list();
    }
    
    @PutMapping("/update")
    public boolean update(@RequestBody Menu menu) {
        UpdateWrapper<Menu> updateWrapper = new UpdateWrapper<>();
        if (null != menu) {
            updateWrapper.set("name", menu.getName())
                    .set("price", menu.getPrice())
                    .set("flavor", menu.getFlavor())
                    .eq("id", menu.getId());
            if (null != menu.getType()) updateWrapper.set("tid", menu.getType().getId());
            else updateWrapper.set("tid", null);
        } else return false;
        return menuService.update(updateWrapper);
    }
    
    @PostMapping("/save")
    public boolean save(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }
    
    @GetMapping("/search/{param}")
    public List<Menu> search(@PathVariable String param) {
        return menuService.search(param);
    }
    
    @GetMapping("/getFlavors")
    public List<String> getFlavors() {
        List<String> falvors = new ArrayList<>();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("flavor").groupBy("flavor");
        menuService.list(queryWrapper).forEach(menu -> {
            falvors.add(menu.getFlavor());
        });
        return falvors;
    }
}
