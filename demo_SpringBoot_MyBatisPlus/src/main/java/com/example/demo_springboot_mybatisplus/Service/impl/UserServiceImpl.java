package com.example.demo_springboot_mybatisplus.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo_springboot_mybatisplus.DO.User;
import com.example.demo_springboot_mybatisplus.Mapper.UserMapper;
import com.example.demo_springboot_mybatisplus.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 用户业务接口的具体实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByUsernameAndEmail(String username, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, username)
                .eq(User::getEmail, email);
        return userMapper.selectOne(queryWrapper);
    }
}
