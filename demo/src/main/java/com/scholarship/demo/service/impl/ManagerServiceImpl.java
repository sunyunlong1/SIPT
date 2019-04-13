package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.ManagerDao;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.Teacher;
import com.scholarship.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;

    @Autowired
    StudentDao studentDao;

    @Override
    public ManagerTableDto currentProcess(String account) {
        int index = 0;
        ManagerTableDto managerDtos = new ManagerTableDto();
        UnifiedTable unifiedTable =
                managerDao.managerUnifiedTable(account);
        List<ManagerDto> firstManagerDtos = new ArrayList<>();
        List<ManagerDto> secondManagerDtos = new ArrayList<>();
        Map<String,List<ManagerDto>> result = new HashMap<>();

        Project projectFirst = managerDao.selectByStatus("立项");
        if(projectFirst!=null){
            unifiedTable.setCurrentProcess(projectFirst.getYears()+"立项");
        }else {
            Project project = managerDao.selectByStatus("中期检查");
            Project projectEnd = managerDao.selectByStatus("结题");
            unifiedTable.setCurrentProcess(project.getYears() + "中期检查" + "/" +projectEnd.getYears()+"结题");
        }

        if(unifiedTable.getCurrentProcess().contains("立项")){

            firstManagerDtos = managerDao.currentProcess("立项");
            result.put(unifiedTable.getCurrentProcess(),firstManagerDtos);
            for(ManagerDto managerDto : firstManagerDtos){
                if (managerDto.getOneGrade() == 0
                        || managerDto.getTwoGrade() == 0
                        || managerDto.getThreeGrade() == 0
                        || managerDto.getFourGrade() == 0
                        || managerDto.getPgAvg() == 0.00){
                    index++;
                }
            }
            if(index == 0){
                unifiedTable.setLevel("审批完成"+firstManagerDtos.size()+"/"+firstManagerDtos.size());
            }else{
                unifiedTable.setLevel("正在审批"+index+"/"+firstManagerDtos.size());
            }
        }else{
            firstManagerDtos = managerDao.currentProcess("中期检查");
            secondManagerDtos = managerDao.currentProcess("结题");
            String[] split = unifiedTable.getCurrentProcess().split("/");
            result.put(split[0],firstManagerDtos);
            result.put(split[1],secondManagerDtos);
            for(ManagerDto managerDto : firstManagerDtos){
                if (managerDto.getOneGrade() == 0
                        || managerDto.getTwoGrade() == 0
                        || managerDto.getThreeGrade() == 0
                        || managerDto.getFourGrade() == 0
                        || managerDto.getPgAvg() == 0.00){
                    index++;
                }
            }
            for(ManagerDto managerDto : secondManagerDtos){
                if (managerDto.getOneGrade() == 0
                        || managerDto.getTwoGrade() == 0
                        || managerDto.getThreeGrade() == 0
                        || managerDto.getFourGrade() == 0
                        || managerDto.getPgAvg() == 0.00){
                    index++;
                }
            }
            int size = firstManagerDtos.size()+secondManagerDtos.size();
            if(index == 0){
                unifiedTable.setLevel("审批完成"+size+"/"+size);
            }else{
                unifiedTable.setLevel("正在审批"+index+"/"+size);
            }
        }
        managerDtos.setUnifiedTable(unifiedTable);
        managerDtos.setManagerDtoList(result);
        return managerDtos;
    }

    @Override
    public String apply(List<ManagerDto> managerDtoList) {

        for(ManagerDto managerDto : managerDtoList){
            Student student = managerDao.selecyByUserName(managerDto.getUserName());
            Project project = studentDao.selectByLeaderAccount(student.getAccount());
            managerDao.selectById(project.getSAccount(),project.getPStatus(),managerDto.getLevel());
        }
        return "提交结果成功";
    }

    @Override
    public String stop(Map<String,List<ManagerDto>> managerDto) {
        Set<String> strings = managerDto.keySet();
        for(String status : strings){
            List<ManagerDto> managerDtos = managerDto.get(status);
            for(ManagerDto manager : managerDtos){
                Student student = managerDao.selecyByUserName(manager.getUserName());
                //todo 更新 if(status.lenght()>6) -->中期检查 else -->结题
                if(status.length()>6){
                    managerDao.stop("中期检查",student.getAccount());
                }else{
                    managerDao.stop("结题",student.getAccount());
                }
            }
        }
        return "已改为不可收取";
    }

    @Override
    public List<OverviewResponse> overview() {
        List<OverviewResponse> resultList = new ArrayList<>();
        String[] strings = {"2017","2018","2019"};
        for (int i = 0; i < strings.length; i++) {
            OverviewResponse overviewResponse = new OverviewResponse();
            overviewResponse.setName(strings[i]);
            Integer sum = managerDao.sum(strings[i]);
            overviewResponse.setSum(sum);
            String status = managerDao.getStatus(strings[i]);
            overviewResponse.setpStatus(status);
            resultList.add(overviewResponse);
        }
        return resultList;
    }

    @Override
    public List<ManagerViewProject> details(OverviewResponse overview) {
        List<ManagerViewProject> result = new ArrayList<>();
        String years = overview.getName().substring(0, 3);
        List<Project> projects = managerDao.selectByYears(years);
        for(Project project : projects){
            ManagerViewProject managerViewProject = new ManagerViewProject();
            managerViewProject.setpName(project.getPName());
            Student student = studentDao.selectByAccount(project.getSAccount());
            managerViewProject.setLeaderUserName(student.getUserName());
            managerViewProject.setCollege(student.getCollege());
            Teacher teacher = studentDao.getTeacherUserName(project.getTAccount());
            managerViewProject.setTeacherName(teacher.getUserName());
            result.add(managerViewProject);
        }
        return result;
    }
}
