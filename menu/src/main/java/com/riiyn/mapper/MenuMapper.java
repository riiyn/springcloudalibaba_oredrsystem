package com.riiyn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riiyn.entity.Menu;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    
    IPage<Menu> getPageVo(Page<Menu> page);
    
    List<Menu> search(String param);
    
    @Insert("insert into t_menu values(#{id}, #{name}, #{price}, #{flavor}, #{type.id})")
    boolean addMenu(Menu menu);
}
