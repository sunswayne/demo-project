package com.sun.demo.controller;

import com.sun.demo.bean.User;
import com.sun.demo.service.IUserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
public class DemoController {

    @Resource
    IUserService userService;

    @RequestMapping("/user")
    public User hello(@RequestParam("id") String id) {
        User user = userService.getUserById(id);
        return user;
    }

}