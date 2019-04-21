package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.ManagerDao;
import com.scholarship.demo.model.Admin;
import com.scholarship.demo.model.PGrade;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.SiptProcess;
import com.scholarship.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;


    @Override
    public ManagerTableDto currentProcess(String account) {
        int index = 0;
        ManagerTableDto result = new ManagerTableDto();
        UnifiedTable unifiedTable = new UnifiedTable();
        Map<String, List<ManagerDto>> resultMap = new HashMap<>();
        List<ManagerDto> managerDtos = new ArrayList<>();
        Admin admin = managerDao.selectById(account);
        unifiedTable.setLevel(admin.getLevel());
        List<SiptProcess> siptProcessList = managerDao.selectByConduct("流程中");
        if (siptProcessList.size() == 0) {
            return null;
        } else {
            for (SiptProcess siptProcess : siptProcessList) {
                List<Project> projects = managerDao.selectBySidYear(admin.getCollege(), siptProcessList.get(0).getYear());
                for (Project project : projects) {
                    ManagerDto managerDto = new ManagerDto();
                    managerDto.setCollege(project.getCollege());
                    managerDto.setLeaderName(project.getSName());
                    managerDto.setLeaderAccount(project.getSAccount());
                    managerDto.setTName(project.getTName());
                    managerDto.setPType(project.getPType());
                    managerDto.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                    PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(), siptProcessList.get(0).getYear(), siptProcess.getStatus());
                    if (pGrade.getOneGrade() == 0
                            || pGrade.getTwoGrade() == 0
                            || pGrade.getThreeGrade() == 0
                            || pGrade.getFourGrade() == 0) {
                        index++;
                    }
                    managerDto.setOneGrade(pGrade.getOneGrade());
                    managerDto.setTwoGrade(pGrade.getTwoGrade());
                    managerDto.setThreeGrade(pGrade.getThreeGrade());
                    managerDto.setFourGrade(pGrade.getFourGrade());
                    managerDto.setPgAvg(pGrade.getPgAvg());
                    managerDtos.add(managerDto);
                }
                unifiedTable.setCurrentProcess(siptProcessList.get(0).getYear() + siptProcess.getStatus());
                unifiedTable.setState("正在审批" + index + "/" + managerDtos.size());
                resultMap.put(siptProcessList.get(0).getYear() + siptProcess.getStatus(), managerDtos);
                result.setUnifiedTable(unifiedTable);
                result.setManagerDtoList(resultMap);
            }
        }
        return result;
    }

    @Override
    public String apply(Map<String,List<Key>> keyMap) {
        String year = "";
        String account = "";
        Set<String> strings = keyMap.keySet();
        List<Key> keyList = null;
        for(String s : strings){
            keyList = keyMap.get(s);
            account = s;
        }
        Admin admin = managerDao.selectById(account);
        if(admin.getLevel().equals("校级")){
            for (Key key : keyList) {
                String[] split = key.getKey().split("#");
                year = split[0];
                managerDao.UpdatePGradeLevel(split[2], split[0], split[1], key.getLevel());
            }
            managerDao.UpdateConduct(year, "流程结束");
            managerDao.UpdateCollect("已提交",year);
        }else{
            for (Key key : keyList) {
                String[] split = key.getKey().split("#");
                managerDao.UpdatePGradeCLevel(split[2], split[0], split[1], key.getLevel());
            }
        }
        return "提交结果成功";
    }

    @Override
    public String stop(String name) {
        String year = name.substring(0, 4);
        String status = name.substring(4, name.length());

        SiptProcess siptProcess = managerDao.selectByYear(year);
        if (!siptProcess.getIsCollect().equals("收取材料")) {
            return "fail";
        } else {
            managerDao.UpdatePGradeCollect(year, status, "正在审批");
            return "success";
        }
    }

    @Override
    public List<OverviewResponse> overview(String account) {
        List<OverviewResponse> resultList = new ArrayList<>();
        Admin admin = managerDao.selectById(account);
        List<SiptProcess> siptProcesses = managerDao.selectProcess();
        for (SiptProcess siptProcess : siptProcesses) {
            OverviewResponse overviewResponse = new OverviewResponse();
            Integer integer = managerDao.selectSumProject(siptProcess.getYear(), admin.getCollege());
            overviewResponse.setName(siptProcess.getYear() + "SIPT");
            overviewResponse.setSum(integer);
            overviewResponse.setPStatus(siptProcess.getStatus());
            overviewResponse.setIsCollect(siptProcess.getIsCollect());
            overviewResponse.setKey(siptProcess.getYear() + "#" + siptProcess.getStatus());
            resultList.add(overviewResponse);
        }
        return resultList;
    }

    @Override
    public List<ManagerViewProject> details(Key key) {
        String[] split = key.getKey().split("#");
        List<ManagerViewProject> result = new ArrayList<>();
        String year = split[0];
        SiptProcess siptProcess = managerDao.selectByYear(year);
        List<Project> projects = managerDao.selectProjectByYear(year);
        for (Project project : projects) {
            ManagerViewProject managerViewProject = new ManagerViewProject();
            PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(), project.getYear(), siptProcess.getStatus());
            if (pGrade.getLevel() == null || pGrade.getLevel().equals("")) {
                managerViewProject.setAvg("-");
            } else {
                managerViewProject.setAvg(pGrade.getLevel());

            }
            managerViewProject.setTeacherName(project.getTName());
            managerViewProject.setCollege(project.getCollege());
            managerViewProject.setLeaderUserName(project.getSName());
            managerViewProject.setPName(project.getPName());
            managerViewProject.setPSource(project.getPSource());
            managerViewProject.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
            result.add(managerViewProject);
        }
        return result;
    }

    @Override
    public String newAndEditProcess(NewProcessDto newProcessDto) {
        //立项 begin年份+1
        String year = newProcessDto.getBeginTime().substring(0, 4);
        if (newProcessDto.getProcessName().equals("立项")) {

            Integer LYear = Integer.valueOf(year) + 1;
            SiptProcess siptProcess = managerDao.selectByYear(LYear.toString());
            if (siptProcess.getStatus().equals("立项")) {
                return "已存在当前流程，不可重复新建";
            }
            Integer integer = managerDao.insertProcess(LYear.toString(), newProcessDto.getProcessName(), newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料", "流程中");
            //同时新建成绩表中流程
            List<Project> projects = managerDao.selectProjectByYear(LYear.toString());
            for (Project project : projects) {
                managerDao.insertpGrade(project.getSAccount(), project.getSName(), LYear.toString(), "立项");
            }
        } else {
            String[] split = newProcessDto.getProcessName().split("/");
            //同时新建成绩表中流程
            List<Project> projects = managerDao.selectProjectByYear(year);
            Integer JYear = Integer.valueOf(year) - 1;
            SiptProcess siptProcess = managerDao.selectByYear(year);
            SiptProcess YsiptProcess = managerDao.selectByYear(JYear.toString());
            if (siptProcess.getStatus().equals("中期检查") || YsiptProcess.equals("结项")) {
                return "已存在当前流程，不可重复新建";
            }
            for (Project project : projects) {
                managerDao.insertpGrade(project.getSAccount(), project.getSName(), year, split[0]);
                managerDao.insertpGrade(project.getSAccount(), project.getSName(), JYear.toString(), split[1]);
            }
            managerDao.updateProcess(year, split[0], newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料", "流程中");
            managerDao.updateProcess(JYear.toString(), split[1], newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料", "流程中");
        }
        return "成功新建流程";
    }
}
