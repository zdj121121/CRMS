package com.cqupt.service.impl;

import com.cqupt.service.IUserService;
import com.cqupt.service.dao.mapper.UserMapper;
import com.cqupt.service.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserMapper userMapper;

    public User login(Integer id){return userMapper.login(id);}
}
