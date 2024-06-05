package com.example.demo_springboot_mybatisplus.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo_springboot_mybatisplus.DO.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 数据库接口层，用于操作user表的接口类
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
