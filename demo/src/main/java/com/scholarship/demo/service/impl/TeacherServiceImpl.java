package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.TeacherAppRep;
import com.scholarship.demo.api.TeacherApprove;
import com.scholarship.demo.api.TeacherMyProject;
import com.scholarship.demo.dao.TeacherDao;
import com.scholarship.demo.model.PGrade;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.SiptProcess;
import com.scholarship.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Override
    public List<TeacherAppRep> pApproval(String account) {
        List<TeacherAppRep> result = new ArrayList<>();
        List<SiptProcess> siptProcessList = teacherDao.selectByConduct("流程中");
        for(SiptProcess siptProcess : siptProcessList){
            List<Project> projects = teacherDao.selectByTidAndYear(account, siptProcess.getYear(),"已提交");
            if(projects!=null){
                for(Project project : projects){
                    TeacherAppRep teacherAppRep = new TeacherAppRep();
                    teacherAppRep.setCollege(project.getCollege());
                    teacherAppRep.setPName(project.getPName());
                    teacherAppRep.setPSource(project.getPSource());
                    teacherAppRep.setSName(project.getSName());
                    teacherAppRep.setStatus(siptProcess.getStatus());
                    teacherAppRep.setTName(project.getTName());
                    teacherAppRep.setKey(project.getSAccount()+"::"+project.getYear());
                    result.add(teacherAppRep);
                }
            }
        }
        return result;
    }

    @Override
    public String approve(String key) {
        String[] split = key.split("::");
        String year = split[1];
        SiptProcess siptProcess = teacherDao.selectByStatus(year);
        if (!siptProcess.getIsCollect().equals("收取材料")){
            teacherDao.updateTApproval("pass",split[0],split[1],"已驳回");
            return "材料收取已结束，审批失败";
        }else{
            teacherDao.updateTApproval("pass",split[0],split[1],"已审批");
            return "审批成功";
        }
    }

    @Override
    public String notApprove(String key) {
        String[] split = key.split("::");
        teacherDao.updateTApproval("notPass",split[0],split[1],"已驳回");
        return "审批成功";
    }

    @Override
    public List<TeacherMyProject> myProject(String account) {
        List<TeacherMyProject> result = new ArrayList<>();
        List<SiptProcess> siptProcesseList = teacherDao.selectAll();
        for (SiptProcess siptProcess : siptProcesseList){
            List<Project> projects = teacherDao.selectBytIAndYear(account, siptProcess.getYear(),"已审批");
            for(Project project : projects){
                TeacherMyProject teacherMyProject = new TeacherMyProject();
                teacherMyProject.setResult("".equals(project.getAvg()) ? "-" : project.getAvg());
                teacherMyProject.setCollege(project.getCollege());
                teacherMyProject.setLeaderName(project.getSName());
                PGrade pGrade = teacherDao.selectById(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                teacherMyProject.setLevel("".equals(pGrade.getLevel()) ? "-" : pGrade.getLevel());
                teacherMyProject.setPName(project.getPName());
                teacherMyProject.setPSource(project.getPSource());
                teacherMyProject.setStatus(siptProcess.getStatus());
                teacherMyProject.setKey(project.getSAccount()+"::"+project.getYear());
                result.add(teacherMyProject);
            }
        }
        return result;
    }
}
