package com.cqupt.service.impl;

import com.cqupt.service.IUserService;
import com.cqupt.dao.mapper.UserMapper;
import com.cqupt.dao.model.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService{
    @Resource
    UserMapper userMapper;

    public User login(User user){return userMapper.login(user);}
}
