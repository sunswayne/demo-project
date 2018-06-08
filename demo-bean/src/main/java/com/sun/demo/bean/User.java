package com.sun.demo.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户信息
 * Created by Wayne on 2018/06/08.
 */
@Table(name = "sys_user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 用户标识号
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    /**
     * 真实姓名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}