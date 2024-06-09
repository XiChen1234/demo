package com.example.commonexception.controller;

import com.example.commonexception.common.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Description api接口层
 */
@RestController
public class CommonController {

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    /**
     * 三种通用响应类型
     *
     * @param type 类型
     *             success：成功200
     *             warning：警告400
     *             error：错误500
     * @return 通用响应类
     */
    @GetMapping("/common/{type}")
    public CommonResponse<String> response(@PathVariable String type) {
        if (Objects.equals(type, "success")) {
            return CommonResponse.creatForSuccessData("my data is here");
        }

        if (Objects.equals(type, "warning")) {
            return CommonResponse.creatForWarning();
        }

        if (Objects.equals(type, "error")) {
            return CommonResponse.creatForErrorMessage("服务器错误");
        }

        return CommonResponse.creatForWarningMessage("参数判断错误");
    }
}