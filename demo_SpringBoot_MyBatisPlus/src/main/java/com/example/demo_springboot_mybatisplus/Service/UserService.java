package com.example.demo_springboot_mybatisplus.Service;

import com.example.demo_springboot_mybatisplus.DO.User;

/**
 * @Description 用户业务接口
 */
public interface UserService {
    public User getUserByUsernameAndEmail(String username, String email);
}
