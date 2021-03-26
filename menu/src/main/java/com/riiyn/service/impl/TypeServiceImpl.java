package com.riiyn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riiyn.entity.Type;
import com.riiyn.mapper.TypeMapper;
import com.riiyn.service.TypeService;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
}
