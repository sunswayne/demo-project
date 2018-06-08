package com.sun.demo.service.impl;

import com.sun.demo.bean.User;
import com.sun.demo.dao.SystemUserDao;
import com.sun.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {

    @Autowired
    SystemUserDao userDao;

    @Override
    public User getUserById(String id) {
        return userDao.selectByPrimaryKey(id);
    }
}
