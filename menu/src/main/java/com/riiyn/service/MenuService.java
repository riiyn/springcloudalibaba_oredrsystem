package com.riiyn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.riiyn.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    IPage<Menu> getPageVo(Page<Menu> page);
    
    List<Menu> search(String param);
    
    boolean addMenu(Menu menu);
}
