package com.example.commonresponse_demo.service;

import com.example.commonresponse_demo.common.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description 业务层接口的具体实现
 */
@Service
public class MyServiceImpl implements MyService {
    @Override
    public CommonResponse<List<Student>> getStudentList(String type) {
        if (Objects.equals(type, "success")) {
            List<Student> studentList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Student student = new Student();
                student.setId(i);
                student.setName("张" + i);
                student.setAge(18 + i);
                student.setEmail("张" + i + "@qq.com");

                studentList.add(student);
            }
            return CommonResponse.creatForSuccessData(studentList);
        } else if (Objects.equals(type, "warning")) {
            return CommonResponse.creatForWarningMessage("客户端请求异常");
        } else if (Objects.equals(type, "error")){
            return CommonResponse.creatForErrorMessage("服务端出现错误");
        }

        return CommonResponse.creatForWarningMessage("服务端出现错误，路径参数类型不符合");
    }
}
