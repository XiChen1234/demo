package com.example.commonresponse_demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Objects;

/**
 * @Description 通用响应类结构
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // 空数据不包含
public class CommonResponse<T> {
    private final Integer code;
    private final String message;
    private final T data;

    // 私有构造方法
    private CommonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // --- 成功响应 --- //

    /**
     * 创建成功响应
     *
     * @return 带code的成功响应
     */
    public static <T> CommonResponse<T> creatForSuccess() {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }

    /**
     * 创建成功响应 message
     *
     * @return 带code message的成功响应
     */
    public static <T> CommonResponse<T> creatForSuccessMessage(String message) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), message, null);
    }

    /**
     * 创建成功响应 data
     *
     * @return 带code data的成功响应
     */
    public static <T> CommonResponse<T> creatForSuccessData(T data) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    /**
     * 创建成功响应 message data
     *
     * @return 带code message data的成功响应
     */
    public static <T> CommonResponse<T> creatForSuccessMessageData(String message, T data) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    // --- 警告响应 --- //

    /**
     * 创建警告响应
     *
     * @return 警告响应
     */
    public static <T> CommonResponse<T> creatForWarning() {
        return new CommonResponse<>(ResponseCode.WARNING.getCode(), ResponseCode.ERROR.getMessage(), null);
    }

    /**
     * 创建警告响应
     *
     * @param message 自定义信息
     * @return 警告响应
     */
    public static <T> CommonResponse<T> creatForWarningMessage(String message) {
        return new CommonResponse<>(ResponseCode.WARNING.getCode(), message, null);
    }

    // ---  异常响应 --- //

    /**
     * 创建错误响应
     *
     * @return 警告响应
     */
    public static <T> CommonResponse<T> creatForError() {
        return new CommonResponse<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage(), null);
    }

    /**
     * 创建错误响应
     *
     * @param message 自定义信息
     * @return 警告响应
     */
    public static <T> CommonResponse<T> creatForErrorMessage(String message) {
        return new CommonResponse<>(ResponseCode.ERROR.getCode(), message, null);
    }
}
