package com.example.demo_springboot_mybatisplus.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description 用户，数据库DO对象，与数据库表一一对应
 */
@Data
@TableName("user")
public class User {
    @TableId("id") // 表id的注解，映射不一致时写入
    private Integer user_id;
    @TableField("name") // 字段value，映射不一致时写入
    private String username;
    private Integer age;
    private String email;
}
