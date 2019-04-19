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
    public List<TeacherAppRep> pApproval(String account, String year) {
        List<TeacherAppRep> result = new ArrayList<>();
        SiptProcess siptProcess = teacherDao.selectByYear(year);
        List<Project> projects = teacherDao.selectByTidAndYear(account, year);
        for(Project project : projects){
            TeacherAppRep teacherAppRep = new TeacherAppRep();
            teacherAppRep.setCollege(project.getCollege());
            teacherAppRep.setPName(project.getPName());
            teacherAppRep.setPSource(project.getPSource());
            teacherAppRep.setSName(project.getSName());
            teacherAppRep.setStatus(siptProcess.getStatus());
            teacherAppRep.setTName(project.getTName());
            result.add(teacherAppRep);
        }
        if(siptProcess.getStatus().equals("立项")){
            return result;
        }else if(siptProcess.getStatus().equals("中期检查")){
            Integer lastYear = Integer.valueOf(year)-1;
            SiptProcess lastSiptProcess = teacherDao.selectByYear(lastYear.toString());
            List<Project> lastProjects = teacherDao.selectByTidAndYear(account, lastYear.toString());
            for(Project project : lastProjects){
                TeacherAppRep teacherAppRep = new TeacherAppRep();
                teacherAppRep.setCollege(project.getCollege());
                teacherAppRep.setPName(project.getPName());
                teacherAppRep.setPSource(project.getPSource());
                teacherAppRep.setSName(project.getSName());
                teacherAppRep.setStatus(lastSiptProcess.getStatus());
                teacherAppRep.setTName(project.getTName());
                result.add(teacherAppRep);
            }
        }else if(siptProcess.getStatus().equals("结题")){
            Integer nextYear = Integer.valueOf(year)+1;
            SiptProcess nextSiptProcess = teacherDao.selectByYear(nextYear.toString());
            List<Project> nextProjects = teacherDao.selectByTidAndYear(account, nextYear.toString());
            for(Project project : nextProjects){
                TeacherAppRep teacherAppRep = new TeacherAppRep();
                teacherAppRep.setCollege(project.getCollege());
                teacherAppRep.setPName(project.getPName());
                teacherAppRep.setPSource(project.getPSource());
                teacherAppRep.setSName(project.getSName());
                teacherAppRep.setStatus(nextSiptProcess.getStatus());
                teacherAppRep.setTName(project.getTName());
                result.add(teacherAppRep);
            }

        }
        return result;
    }

    @Override
    public String approve(TeacherApprove teacherApprove) {
        SiptProcess siptProcess = teacherDao.selectByStatus(teacherApprove.getStatus());
        teacherDao.updateTApproval(teacherApprove.getLeaderName(),siptProcess.getYear());
        return "审批成功";
    }

    @Override
    public List<TeacherMyProject> myProject(String account) {
        List<TeacherMyProject> result = new ArrayList<>();
        List<SiptProcess> siptProcesseList = teacherDao.selectAll();
        for (SiptProcess siptProcess : siptProcesseList){
            List<Project> projects = teacherDao.selectBytIAndYear(account, siptProcess.getYear());
            for(Project project : projects){
                TeacherMyProject teacherMyProject = new TeacherMyProject();
                teacherMyProject.setAvg(project.getAvg());
                teacherMyProject.setCollege(project.getCollege());
                teacherMyProject.setLeaderName(project.getSName());
                PGrade pGrade = teacherDao.selectById(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                teacherMyProject.setLevel(pGrade.getLevel());
                teacherMyProject.setPName(project.getPName());
                teacherMyProject.setPSource(project.getPSource());
                teacherMyProject.setStatus(siptProcess.getStatus());
                result.add(teacherMyProject);
            }
        }
        return result;
    }
}
