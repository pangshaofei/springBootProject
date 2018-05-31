package com.common.project.service;

import com.common.project.entity.DO.User;
import com.common.project.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.selectAll();
    }
}
