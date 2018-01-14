package com.cqupt.service;

import com.cqupt.dao.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    User login(User user);

    List<User> getUserInfo(Map<String,Object> map);

    int getTotal();
}
