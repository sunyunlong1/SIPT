package com.scholarship.demo.service;

import com.scholarship.demo.api.*;
import com.scholarship.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ManagerService {

    /**
     * 当前流程
     * @param account 账号
     * @return
     */
    ManagerTableDto currentProcess(String account);

    /**
     * 提交结果
     * @param keyMap 只需要传年份+状态，姓名,等级
     * @return
     */
    String apply(Map<String,List<Key>> keyMap);

    /**
     * 停止收取
     * @param name 只需要传年份+状态
     * @return
     */
    String stop(String name);

    /**
     * 项目概览
     * @param account 只传账号
     * @return
     */
    OverViewDto overview(String account);

    /**
     * 项目概览查看详情 只需要传 2017SIPT 和 状态
     * @param
     * @return
     */
    List<ManagerViewProject> details(Key key);

    /**
     * 新建流程 传流程类型，开始时间/结束时间
     * @param newProcessDto
     * @return
     */
    String newAndEditProcess(NewProcessDto newProcessDto);

    /**
     * 查询学生信息
     * @param account
     * @return
     */
    List<Student> find(String account,String saccount);

    /**
     * 增加学生账户
     * @param student
     * @return
     */
    String add(Student student);

    /**
     * 更新学生
     * @param student
     * @return
     */
    String update(Student student);

    /**
     * 删除学生
     * @param account
     * @return
     */
    String delete(String account);
}
