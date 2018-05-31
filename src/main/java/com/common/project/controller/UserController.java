package com.common.project.controller;

import com.common.project.entity.DO.User;
import com.common.project.service.UserService;

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
    public List<User> userIndex(){
      return  userService.getAll();
    }
}
