package com.sun.demo.dao;

import com.sun.demo.bean.ResultMessage;
import com.sun.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SystemUserDao {

    @Select("SELECT * FROM sys_user WHERE u_id = #{id}")
    User findById(@Param("id") String id);

    @Select("SELECT * FROM sys_user WHERE u_id = #{username}")
    User findByName(String username);
}