package com.scholarship.demo.service;

import com.scholarship.demo.api.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StudentService {


    /**
     * 当前流程查询接口
     * @param
     * @return
     */
    CurrentProcessRep currentPorcess(String account);

    /**
     * 下载前查询存放地址
     * @param leaderAccount
     * @return
     */
    String selectById(String leaderAccount);

    /**
     * 学生保存
     * @param studentRequestDto 传这个对象
     * @return
     */
    String studentSave(StudentRequestDto studentRequestDto);

    /**
     * 学生提交
     * @param studentRequestDto 传这个对象
     * @return
     */
    String studentApply(StudentRequestDto studentRequestDto);

    /**
     * 学生编辑
     * @param key 传studentId，year
     * @return
     */
    Map<String,Object> edit(Key key);

    /**
     * 我的项目查询数据接口
     * @param leaderAccount 传登陆id
     * @return
     */
    List<MyProjectDto> myProject(String leaderAccount);

    /**
     * 统一登陆管理
     * @param loginDto
     * @return
     */
    LoginResponse login(LoginDto loginDto);
}
