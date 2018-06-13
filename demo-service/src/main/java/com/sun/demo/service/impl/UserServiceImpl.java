package com.sun.demo.service.impl;

import com.sun.demo.bean.ResultMessage;
import com.sun.demo.bean.User;
import com.sun.demo.dao.SystemUserDao;
import com.sun.demo.service.IUserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    SystemUserDao userDao;

    @Override
    public User getUserById(String id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userDao.findByName(username);
    }
}
