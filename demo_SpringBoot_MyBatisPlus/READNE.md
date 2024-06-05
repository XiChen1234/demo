# Spring Boot集成MyBatis Plus项目示例
> 该项目旨在整合我在写技术博客的过程中，所演示的demo项目，仅供个人学习、复习使用，不建议直接用在生产环境中

目前所接触到的大多数项目都是通过Spring Boot快速搭建，敏捷开发的，并且Maven导入的方式也非常方便，所以这个方法是最常用的方法

## 导入过程

### 版本信息
- JDK: `1.8`
- Spring Boot: `2.6.13`
- mysql-connector-jave: `8.0.31`
- **MyBatis Plus**: `3.5.6`

### 引入Maven依赖
在Spring Boot项目中的pom.xml文件中添加MyBatis Plus及MySQL数据库的依赖，为了后面简化DO对象的编写，也引入lombok的依赖
```xml
<!-- mysql -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<!-- mybatis-plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.6</version>
</dependency>


<!-- lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### 数据库初始化
在MySQL中创建新的数据库，并导入部分示例数据：  
| id  | name   | age | email              |
| --- | ------ | --- | ------------------ |
| 1   | Jone   | 18  | test1@baomidou.com |
| 2   | Jack   | 20  | test2@baomidou.com |
| 3   | Tom    | 28  | test3@baomidou.com |
| 4   | Sandy  | 21  | test4@baomidou.com |
| 5   | Billie | 24  | test5@baomidou.com |

sql语句如下：
```sql
-- 数据库表结构
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

-- 数据库数据
DELETE FROM `user`;

INSERT INTO `user` (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

### 项目配置
在项目中的application.yml配置文件中设置数据库连接配置
```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://127.0.0.1:3306/demo
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### 创建数据库DO对象
创建数据库DO对象，注意，该对象为业务层与数据库之间进行数据传输的对象，基本要与数据库中的字段内容一致
```java
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
```

### 添加Mapper层
添加Mapper层，mapper接口直接集成BaseMapper并对应一个DO对象。BaseMapper接口提供了多种操作数据库的方法，可以直接在上层依赖注入后调用接口方法。也可以在其中自定义操作语句，本文较为基础，因此该扩展略。
```java
/**
 * @Description 数据库接口层，用于操作user表的接口类
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
```

如果不在Mapper接口上面添加@Mapper，则需要在启动类中添加Mapper的扫描文件地址才能生效：`@MapperScan("com.example.项目名.mapper")`，每个项目具体地址不一样

### 添加Service层与Controller层
根据业务逻辑，添加Service层和Controller层即可，这里以条件查询用户为例子

service层：
```java
/**
 * @Description 用户业务接口
 */
public interface UserService {
    public User getUserByUsernameAndEmail(String username, String email);
}

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
```

controller层
```java
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
```

随后访问：http://localhost:8080/get?username=Jack&email=test2@baomidou.com，若浏览器出现数据，则证明引入过程成功！
```json
{
  "user_id": null,
  "username": "Jack",
  "age": 20,
  "email": "test2@baomidou.com"
}
```

## 总结
通过以上几个简单的步骤，我们就实现了 User 表的 CRUD 功能，甚至连 XML 文件都不用编写！  
从以上步骤中，我们可以看到集成 MyBatis-Plus 非常的简单，只需要引入 starter 依赖，简单进行配置即可使用。