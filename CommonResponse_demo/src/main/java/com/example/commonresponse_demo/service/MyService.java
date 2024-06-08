package com.example.commonresponse_demo.service;

import com.example.commonresponse_demo.common.CommonResponse;

import java.util.List;

/**
 * @Description 业务层接口
 */
public interface MyService {
    public CommonResponse<List<Student>> getStudentList(String type);
}
