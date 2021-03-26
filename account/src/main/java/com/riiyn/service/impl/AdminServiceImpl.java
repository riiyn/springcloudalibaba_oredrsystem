package com.riiyn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riiyn.entity.Admin;
import com.riiyn.mapper.AdminMapper;
import com.riiyn.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
