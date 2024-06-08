package com.example.commonresponse_demo.controller;

import com.example.commonresponse_demo.common.CommonResponse;
import com.example.commonresponse_demo.service.MyService;
import com.example.commonresponse_demo.service.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description api接口层
 */
@RestController
public class Controller {
    @Resource
    private MyService myService;

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    /**
     * 三种通用响应类型
     * @param type 类型
     *             success：成功200
     *             warning：警告400
     *             error：错误500
     * @return 通用响应类
     */
    @GetMapping("/common/{type}")
    public CommonResponse<List<Student>> getList(@PathVariable String type) {
        return myService.getStudentList(type);
    }
}
