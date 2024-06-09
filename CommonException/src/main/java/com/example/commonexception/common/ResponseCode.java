package com.example.commonexception.common;

import lombok.Getter;

/**
 * @Description 响应码枚举类
 * 枚举项目中所用到的所有响应码
 */
@Getter
public enum ResponseCode {
    SUCCESS(200, "SUCCESS"),
    WARNING(400, "WARNING"),
    ERROR(500, "ERROR"),

    // 在这之后可以添加自定义响应码，一般为自定义异常响应码
    LOGIN_EMPTY(1000, "登录表单提交为空"),
    LOGIN_ERROR(1001, "登录表单验证失败");

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}