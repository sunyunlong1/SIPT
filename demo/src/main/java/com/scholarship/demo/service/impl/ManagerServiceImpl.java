package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.ManagerDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;


    @Override
    public ManagerTableDto currentProcess(String account,String year) {
        int index = 0;
        ManagerTableDto result = new ManagerTableDto();
        UnifiedTable unifiedTable = new UnifiedTable();
        Map<String,List<ManagerDto>> resultMap = new HashMap<>();
        List<ManagerDto> managerDtos = new ArrayList<>();
        Admin admin = managerDao.selectById(account);
        unifiedTable.setLevel(admin.getLevel());
        SiptProcess siptProcess = managerDao.selectByYear(year);
        List<Student> students = managerDao.selectByCollege(admin.getCollege());
        for (Student student : students){
            ManagerDto managerDto = new ManagerDto();
            Project project = managerDao.selectBySidYear(student.getAccount(), year);
            managerDto.setCollege(student.getCollege());
            managerDto.setUserName(student.getUserName());
            managerDto.setTName(project.getTName());
            managerDto.setPType(project.getPType());
            PGrade pGrade = managerDao.selectByIdYStatus(student.getAccount(), year, siptProcess.getStatus());
            if(pGrade.getOneGrade() == 0
                    || pGrade.getTwoGrade() == 0
                    || pGrade.getThreeGrade() == 0
                    || pGrade.getFourGrade() == 0){
                index++;
            }
            managerDto.setOneGrade(pGrade.getOneGrade());
            managerDto.setTwoGrade(pGrade.getTwoGrade());
            managerDto.setThreeGrade(pGrade.getThreeGrade());
            managerDto.setFourGrade(pGrade.getFourGrade());
            managerDto.setPgAvg(pGrade.getPgAvg());
            managerDtos.add(managerDto);
        }
        if (siptProcess.getStatus().equals("立项")){
            unifiedTable.setCurrentProcess(year+siptProcess.getStatus());
            unifiedTable.setState("正在审批"+index+"/"+managerDtos.size());
            resultMap.put(year+siptProcess.getStatus(),managerDtos);
            result.setUnifiedTable(unifiedTable);
            result.setManagerDtoList(resultMap);
        }else if(siptProcess.getStatus().equals("中期检查")){
            List<ManagerDto> managerDtotwo = new ArrayList<>();

            Integer lastYear = Integer.valueOf(year)-1;
            SiptProcess lastProcess = managerDao.selectByYear(lastYear.toString());
            unifiedTable.setCurrentProcess(year+siptProcess.getStatus()+"/"+lastProcess.getStatus());
            for (Student student : students){
                ManagerDto managerDto = new ManagerDto();
                Project project = managerDao.selectBySidYear(student.getAccount(), lastYear.toString());
                managerDto.setCollege(student.getCollege());
                managerDto.setUserName(student.getUserName());
                managerDto.setTName(project.getTName());
                managerDto.setPType(project.getPType());
                PGrade pGrade = managerDao.selectByIdYStatus(student.getAccount(), lastYear.toString(), lastProcess.getStatus());
                if(pGrade.getOneGrade() == 0
                        || pGrade.getTwoGrade() == 0
                        || pGrade.getThreeGrade() == 0
                        || pGrade.getFourGrade() == 0){
                    index++;
                }
                managerDto.setOneGrade(pGrade.getOneGrade());
                managerDto.setTwoGrade(pGrade.getTwoGrade());
                managerDto.setThreeGrade(pGrade.getThreeGrade());
                managerDto.setFourGrade(pGrade.getFourGrade());
                managerDto.setPgAvg(pGrade.getPgAvg());
                managerDtotwo.add(managerDto);
            }
            unifiedTable.setState("正在审批"+index+"/"+managerDtos.size()+managerDtotwo.size());
            resultMap.put(year+siptProcess.getStatus(),managerDtos);
            resultMap.put(lastYear+lastProcess.getStatus(),managerDtotwo);

        }else if(siptProcess.getStatus().equals("结题")){
            List<ManagerDto> managerDtothree = new ArrayList<>();

            Integer nextYear = Integer.valueOf(year)+1;
            SiptProcess nextProcess = managerDao.selectByYear(nextYear.toString());
            unifiedTable.setCurrentProcess(nextProcess+nextProcess.getStatus()+"/"+year+siptProcess.getStatus());

            for (Student student : students){
                ManagerDto managerDto = new ManagerDto();
                Project project = managerDao.selectBySidYear(student.getAccount(), nextYear.toString());
                managerDto.setCollege(student.getCollege());
                managerDto.setUserName(student.getUserName());
                managerDto.setTName(project.getTName());
                managerDto.setPType(project.getPType());
                PGrade pGrade = managerDao.selectByIdYStatus(student.getAccount(), nextYear.toString(), nextProcess.getStatus());
                if(pGrade.getOneGrade() == 0
                        || pGrade.getTwoGrade() == 0
                        || pGrade.getThreeGrade() == 0
                        || pGrade.getFourGrade() == 0){
                    index++;
                }
                managerDto.setOneGrade(pGrade.getOneGrade());
                managerDto.setTwoGrade(pGrade.getTwoGrade());
                managerDto.setThreeGrade(pGrade.getThreeGrade());
                managerDto.setFourGrade(pGrade.getFourGrade());
                managerDto.setPgAvg(pGrade.getPgAvg());
                managerDtothree.add(managerDto);
            }
            unifiedTable.setState("正在审批"+index+"/"+managerDtos.size()+managerDtothree.size());
            resultMap.put(year+siptProcess.getStatus(),managerDtos);
            resultMap.put(nextYear+nextProcess.getStatus(),managerDtothree);
        }
        return result;
    }

    @Override
    public String apply(Map<String,List<ManagerDto>> managerDtoMap) {
        List<ManagerDto> managerDtos = null;
        String year = "";
        String status = "";
        Set<String> strings = managerDtoMap.keySet();
        for(String s: strings){
            managerDtos = managerDtoMap.get(s);
            year = s.substring(0,3);
            status = s.substring(4,s.length());
        }
        for(ManagerDto managerDto : managerDtos){
            managerDao.UpdatePGradeLevel(managerDto.getUserName(),year,status,managerDto.getLevel());
        }
        return "提交结果成功";
    }

    @Override
    public String stop(Map<String,List<ManagerDto>> managerDtoMap) {
        List<ManagerDto> managerDtos = null;
        String year = "";
        String status = "";
        Set<String> strings = managerDtoMap.keySet();
        for(String s: strings){
            managerDtos = managerDtoMap.get(s);
            year = s.substring(0,3);
            status = s.substring(4,s.length());
        }
        for(ManagerDto managerDto : managerDtos){
            managerDao.UpdatePGradeCollect(managerDto.getUserName(),year,status,"已停止收取");
        }
        return "已改为不可收取";
    }

    @Override
    public List<OverviewResponse> overview(String account) {
        List<OverviewResponse> resultList = new ArrayList<>();
        Admin admin = managerDao.selectById(account);
        List<SiptProcess> siptProcesses = managerDao.selectProcess();
        for (SiptProcess siptProcess : siptProcesses){
            OverviewResponse overviewResponse = new OverviewResponse();
            Integer integer = managerDao.selectSumProject(siptProcess.getYear(), admin.getCollege());
            overviewResponse.setName(siptProcess.getYear()+"SIPT");
            overviewResponse.setSum(integer);
            overviewResponse.setPStatus(siptProcess.getStatus());
            resultList.add(overviewResponse);
        }
        return resultList;
    }

    @Override
    public List<ManagerViewProject> details(String name) {
        List<ManagerViewProject> result = new ArrayList<>();
        String year = name.substring(0, 4);
        List<Project> projects = managerDao.selectProjectByYear(year);
        for (Project project : projects){
            ManagerViewProject managerViewProject = new ManagerViewProject();
            managerViewProject.setAvg(project.getAvg());
            managerViewProject.setTeacherName(project.getTName());
            managerViewProject.setCollege(project.getCollege());
            managerViewProject.setLeaderUserName(project.getSName());
            managerViewProject.setPName(project.getPName());
            result.add(managerViewProject);
        }
        return result;
    }

    @Override
    public String newAndEditProcess(NewProcessDto newProcessDto) {
        String year = newProcessDto.getBeginTime().substring(0, 4);
        if(newProcessDto.getProcessName().equals("立项")){
            Integer integer = managerDao.insertProcess(year, newProcessDto.getProcessName(), newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料");
        }else{
            String[] split = newProcessDto.getProcessName().split("/");
            managerDao.updateProcess(year,split[0]);
            Integer lastYear = Integer.valueOf(year)-1;
            managerDao.updateProcess(lastYear.toString(),split[1]);
        }
        return "成功新建流程";
    }
}
