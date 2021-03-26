package com.riiyn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riiyn.entity.Menu;
import com.riiyn.mapper.MenuMapper;
import com.riiyn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public IPage<Menu> getPageVo(Page<Menu> page) {
        return menuMapper.getPageVo(page);
    }
    
    @Override
    public List<Menu> search(String param) {
        return menuMapper.search(param);
    }
    
    @Override
    public boolean addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }
}
