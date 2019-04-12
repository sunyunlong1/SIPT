package com.scholarship.demo.service;

import com.scholarship.demo.api.MyProjectDto;
import com.scholarship.demo.api.StudentRequestDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface studentService {

    /**
     * 下载前查询存放地址
     * @param leaderAccount
     * @return
     */
    String selectById(String leaderAccount);

    /**
     * 学生保存
     * @param studentRequestDto
     * @return
     */
    String studentSave(StudentRequestDto studentRequestDto);

    /**
     * 学生提交
     * @param studentRequestDto
     * @return
     */
    String studentApply(StudentRequestDto studentRequestDto);

    /**
     * 学生编辑
     * @param leaderAccount
     * @return
     */
    Map<String,Object> studentEdit(String leaderAccount);

    /**
     * 我的项目查询数据接口
     * @param leaderAccount
     * @return
     */
    MyProjectDto myProject(String leaderAccount);
}
