package com.common.project.mapper;

import com.common.project.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    List<User> selectAll();
}
