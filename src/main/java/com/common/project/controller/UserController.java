package com.common.project.controller;

import com.common.project.entity.DO.User;
import com.common.project.service.UserService;

import com.common.project.util.CryptoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CryptoUtil cryptoUtil;

    @RequestMapping("/index")
    @ResponseBody
    public Object userIndex(){
        PageHelper.startPage(1,1);
        PageInfo<User> pageInfo = new PageInfo<>(userService.getAll());
        return  pageInfo;
    }

    @GetMapping("/sendPassword")
    @ResponseBody
    public Object sendPassword(String password){
        String  reValue = cryptoUtil.get3DESEncrypt(password,cryptoUtil.DEFAULT_SECRET_KEY1);
        reValue = reValue.trim().intern();
       return reValue;
    }


    @GetMapping("/getPassword")
    @ResponseBody
    public Object getPassword(String passoord){
        String reValue2 = cryptoUtil.get3DESDecrypt(passoord,cryptoUtil.DEFAULT_SECRET_KEY1);
        return reValue2;
    }
}
