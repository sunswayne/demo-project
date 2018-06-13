package com.sun.demo.service;

import com.sun.demo.bean.User;

/**
 * Created by Wayne on 2018/06/08.
 */
public interface IUserService {

    User getUserById(String id);

    User getUserByUserName(String username);
}
