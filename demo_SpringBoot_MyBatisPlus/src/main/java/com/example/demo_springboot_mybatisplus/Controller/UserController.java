package com.example.demo_springboot_mybatisplus.Controller;

import com.example.demo_springboot_mybatisplus.DO.User;
import com.example.demo_springboot_mybatisplus.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 用户接口层，包含用户相关操作
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/get")
    public User getUser(@RequestParam String username, @RequestParam String email) {
        return userService.getUserByUsernameAndEmail(username, email);
    }
}
