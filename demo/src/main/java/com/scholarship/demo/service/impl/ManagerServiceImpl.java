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
        List<Project> projects = managerDao.selectBySidYear(admin.getCollege(), year);

        for (Project project : projects){
            ManagerDto managerDto = new ManagerDto();
            managerDto.setCollege(project.getCollege());
            managerDto.setLeaderName(project.getSName());
            managerDto.setLeaderAccount(project.getSAccount());
            managerDto.setTName(project.getTName());
            managerDto.setPType(project.getPType());
            PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(), year, siptProcess.getStatus());
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
            List<Project> lastProjects = managerDao.selectBySidYear(admin.getCollege(), lastYear.toString());

            for (Project project : lastProjects){
                ManagerDto managerDto = new ManagerDto();
                managerDto.setCollege(project.getCollege());
                managerDto.setLeaderName(project.getSName());
                managerDto.setLeaderAccount(project.getSAccount());
                managerDto.setTName(project.getTName());
                managerDto.setPType(project.getPType());
                PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(),lastYear.toString(), lastProcess.getStatus());
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
            int sum = managerDtos.size() + managerDtotwo.size();
            unifiedTable.setState("正在审批"+index+"/"+sum);
            resultMap.put(year+siptProcess.getStatus(),managerDtos);
            resultMap.put(lastYear+lastProcess.getStatus(),managerDtotwo);
            result.setUnifiedTable(unifiedTable);
            result.setManagerDtoList(resultMap);
        }else if(siptProcess.getStatus().equals("结题")){
            List<ManagerDto> managerDtothree = new ArrayList<>();

            Integer nextYear = Integer.valueOf(year)+1;
            SiptProcess nextProcess = managerDao.selectByYear(nextYear.toString());
            unifiedTable.setCurrentProcess(nextProcess+nextProcess.getStatus()+"/"+year+siptProcess.getStatus());
            List<Project> nextProjects = managerDao.selectBySidYear(admin.getCollege(), nextYear.toString());

            for (Project project : nextProjects){
                ManagerDto managerDto = new ManagerDto();
                managerDto.setCollege(project.getCollege());
                managerDto.setLeaderName(project.getSName());
                managerDto.setLeaderAccount(project.getSAccount());
                managerDto.setTName(project.getTName());
                managerDto.setPType(project.getPType());
                PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(), nextYear.toString(), nextProcess.getStatus());
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
            int sum = managerDtos.size() + managerDtothree.size();
            unifiedTable.setState("正在审批"+index+"/"+sum);
            resultMap.put(year+siptProcess.getStatus(),managerDtos);
            resultMap.put(nextYear+nextProcess.getStatus(),managerDtothree);
            result.setUnifiedTable(unifiedTable);
            result.setManagerDtoList(resultMap);
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
            year = s.substring(0,4);
            status = s.substring(4,s.length());
        }
        for(ManagerDto managerDto : managerDtos){
            managerDao.UpdatePGradeLevel(managerDto.getLeaderAccount(),year,status,managerDto.getLevel());
        }
        return "提交结果成功";
    }

    @Override
    public String stop(String name) {
        String year = name.substring(0,4);
        String status = name.substring(4,name.length());

        SiptProcess siptProcess = managerDao.selectByYear(year);
        if(siptProcess.getIsCollect().equals("已停止收取")){
            return "fail";
        }else{
            managerDao.UpdatePGradeCollect(year,status,"已停止收取");
            return "success";
        }
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
            overviewResponse.setIsCollect(siptProcess.getIsCollect());
            resultList.add(overviewResponse);
        }
        return resultList;
    }

    @Override
    public List<ManagerViewProject> details(String name) {
        List<ManagerViewProject> result = new ArrayList<>();
        String year = name.substring(0, 4);
        SiptProcess siptProcess = managerDao.selectByYear(year);
        List<Project> projects = managerDao.selectProjectByYear(year);
        for (Project project : projects){
            ManagerViewProject managerViewProject = new ManagerViewProject();
            PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(), project.getYear(), siptProcess.getStatus());
            if (pGrade.getLevel() == null || pGrade.getLevel().equals("")){
                managerViewProject.setAvg("-");
            }else{
                managerViewProject.setAvg(pGrade.getLevel());

            }
            managerViewProject.setTeacherName(project.getTName());
            managerViewProject.setCollege(project.getCollege());
            managerViewProject.setLeaderUserName(project.getSName());
            managerViewProject.setPName(project.getPName());
            managerViewProject.setPSource(project.getPSource());
            result.add(managerViewProject);
        }
        return result;
    }

    @Override
    public String newAndEditProcess(NewProcessDto newProcessDto) {
        //立项 begin年份+1
        String year = newProcessDto.getBeginTime().substring(0, 4);
        if(newProcessDto.getProcessName().equals("立项")){
            Integer LYear = Integer.valueOf(year)+1;
            Integer integer = managerDao.insertProcess(LYear.toString(), newProcessDto.getProcessName(), newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料");
            //同时新建成绩表中流程
            List<Project> projects = managerDao.selectProjectByYear(LYear.toString());
            for(Project project : projects){
                managerDao.insertpGrade(project.getSAccount(),project.getSName(),LYear.toString(),"立项");
            }
        }else{
            String[] split = newProcessDto.getProcessName().split("/");
            //同时新建成绩表中流程
            List<Project> projects = managerDao.selectProjectByYear(year);
            Integer JYear = Integer.valueOf(year)-1;
            for(Project project : projects){
                managerDao.insertpGrade(project.getSAccount(),project.getSName(),year,split[0]);
                managerDao.insertpGrade(project.getSAccount(),project.getSName(),JYear.toString(),split[1]);
            }
            managerDao.updateProcess(year,split[0],newProcessDto.getBeginTime(),newProcessDto.getEndTime(),"收取材料");
            managerDao.updateProcess(JYear.toString(),split[1],newProcessDto.getBeginTime(),newProcessDto.getEndTime(),"收取材料");
        }
        return "成功新建流程";
    }
}
