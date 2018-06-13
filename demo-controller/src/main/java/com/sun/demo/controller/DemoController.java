package com.sun.demo.controller;

import com.sun.demo.bean.User;
import com.sun.demo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class DemoController {

    @Resource
    IUserService userService;

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @RequestMapping(value = "/get-user-by-id", method = RequestMethod.GET)
    public User getUserById(@ApiParam(name = "id", required = true, value = "所属元ID") @RequestParam("id") String id) {
        User user = userService.getUserById(id);
        return user;
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的username来获取用户详细信息")
    @RequestMapping(value = "/get-user-by-name", method = RequestMethod.GET)
    public User getUserByUserName(@ApiParam(name = "username", required = true, value = "所属元ID") @RequestParam("username") String username) {
        User user = userService.getUserByUserName(username);
        return user;
    }
}