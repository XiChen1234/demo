package com.example.commonresponse_demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * @Description 通用响应类结构
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // 空数据不包含
public class CommonResponse<T> {
    private final Integer code;
    private String message;
    private T data;

    // 私有构造方法
    private CommonResponse(Integer code) {
        this.code = code;
    }

    private CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private CommonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 创建成功响应方法
     *
     * @return 成功响应
     */
    public static <T> CommonResponse<T> creatForSuccess() {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 创建成功响应方法
     *
     * @param message 成功自定义信息
     * @return 成功响应
     */
    public static <T> CommonResponse<T> creatForSuccessMessage(String message) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), message);
    }

    /**
     * 创建成功响应方法
     *
     * @param data 响应数据
     * @return 成功响应
     */
    public static <T> CommonResponse<T> creatForSuccessData(T data) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    /**
     * 创建成功响应方法
     *
     * @param message 成功自定义信息
     * @param data    响应数据
     * @return 成功响应
     */
    public static <T> CommonResponse<T> creatForSuccessMessageData(String message, T data) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 创建警告响应方法
     *
     * @return 异常响应
     */
    public static <T> CommonResponse<T> creatForWarning() {
        return new CommonResponse<>(ResponseCode.WARNING.getCode());
    }

    /**
     * 创建警告响应方法
     *
     * @param message 警告自定义信息
     * @return 异常响应
     */
    public static <T> CommonResponse<T> creatForWarningMessage(String message) {
        return new CommonResponse<>(ResponseCode.WARNING.getCode(), message);
    }

    /**
     * 创建错误响应方法
     *
     * @return 错误响应
     */
    public static <T> CommonResponse<T> creatForError() {
        return new CommonResponse<>(ResponseCode.ERROR.getCode());
    }

    /**
     * 创建错误响应方法
     *
     * @param message 错误自定义信息
     * @return 错误响应
     */
    public static <T> CommonResponse<T> creatForErrorMessage(String message) {
        return new CommonResponse<>(ResponseCode.ERROR.getCode(), message);
    }
}