package com.common.project.mapper;

import com.common.project.entity.DO.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    /**
     *
     * @return
     */
    List<User> selectAll();
}
