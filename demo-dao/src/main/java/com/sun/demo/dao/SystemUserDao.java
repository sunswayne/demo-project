package com.sun.demo.dao;

import com.chinapopin.framework.datasource.utils.MyMapper;
import com.sun.demo.bean.User;
import org.apache.ibatis.annotations.Delete;

public interface SystemUserDao extends MyMapper<User> {

}