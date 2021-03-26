package com.riiyn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riiyn.entity.User;
import com.riiyn.mapper.UserMapper;
import com.riiyn.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
