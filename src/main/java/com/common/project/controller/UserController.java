package com.common.project.controller;

import com.common.project.entity.DO.User;
import com.common.project.service.UserService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    @ResponseBody
    public Object userIndex(){
        PageHelper.startPage(1,1);
        PageInfo<User> pageInfo = new PageInfo<>(userService.getAll());
        return  pageInfo;
    }
}
