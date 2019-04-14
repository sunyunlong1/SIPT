package com.scholarship.demo.service;

import com.scholarship.demo.api.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ManagerService {

    /**
     * 当前流程
     * @param account
     * @return
     */
    ManagerTableDto currentProcess(String account);

    /**
     * 提交结果
     * @param managerDtoList
     * @return
     */
    String apply(List<ManagerDto> managerDtoList);

    /**
     * 停止收取
     * @param managerDto
     * @return
     */
    String stop(Map<String,List<ManagerDto>> managerDto);

    /**
     * 项目概览
     * @return
     */
    List<OverviewResponse> overview();

    /**
     * 项目概览查看详情
     * @param overview
     * @return
     */
    List<ManagerViewProject> details(OverviewResponse overview);

    /**
     * 新建流程，编辑流程
     * @param newProcessDto
     * @return
     */
    String newAndEditProcess(NewProcessDto newProcessDto);
}
