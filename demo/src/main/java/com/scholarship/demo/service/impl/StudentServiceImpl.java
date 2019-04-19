package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public CurrentProcessRep currentPorcess(String year) {
        CurrentProcessRep result = new CurrentProcessRep();
        SiptProcess siptProcess = studentDao.selectByYear(year);
        result.setProcessName(siptProcess.getYear()+" SIPT "+siptProcess.getStatus());
        result.setIsCollect(siptProcess.getIsCollect());
        result.setStartTime(siptProcess.getStartTime());
        result.setEndTime(siptProcess.getEndTime());
        return result;
    }

    @Override
    public String selectById(String leaderAccount) {
//        Project project = studentDao.selectByLeaderAccount(leaderAccount);
//        if(project == null){
//            return "0";
//        }else if(project.getPathThird()!=null || !project.getPathThird().equals("")){
//            return project.getPathThird();
//        }else if(project.getPathSecond()!=null || !project.getPathSecond().equals("")){
//            return project.getPathSecond();
//        }else{
//            return project.getPathFirst();
//        }
        return "";
    }

    @Override
    public String studentSave(StudentRequestDto studentRequestDto) {

        //先查询是否有记录
        Project projectFirst = studentDao.selectByLeaderAccountAndYear(studentRequestDto.getLeaderAccount(),studentRequestDto.getYear());
        if(projectFirst == null){
            Project project = new Project();
            project.setPName(studentRequestDto.getPName());
            project.setPType(studentRequestDto.getPType());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setSName(studentRequestDto.getLeaderName());
            project.setMemberNum(studentRequestDto.getMemberNum());
            project.setMemberInf(studentRequestDto.getMemberInf());
            Teacher teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTeacherName());
            project.setTAccount(teacherAccount.getAccount());
            project.setTName(studentRequestDto.getTeacherName());
            project.setPSource(studentRequestDto.getPSource());
            project.setPCode(studentRequestDto.getPCode());
            project.setPIntroduction(studentRequestDto.getPIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYear(studentRequestDto.getYear());
            project.setCollege(studentRequestDto.getLeaderCollege());
            project.setRecordState("已保存");
            studentDao.studentSave(project);
        }else{
            studentDao.updatePath(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),studentRequestDto.getYear());
        }
        return "保存成功";
    }

    @Override
    public String studentApply(StudentRequestDto studentRequestDto) {
        //先查询是否有记录
        Project projectFirst = studentDao.selectByLeaderAccountAndYear(studentRequestDto.getLeaderAccount(),studentRequestDto.getYear());
        if(projectFirst == null){
            Project project = new Project();
            project.setPName(studentRequestDto.getPName());
            project.setPType(studentRequestDto.getPType());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setSName(studentRequestDto.getLeaderName());
            project.setMemberNum(studentRequestDto.getMemberNum());
            project.setMemberInf(studentRequestDto.getMemberInf());
            Teacher teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTeacherName());
            project.setTAccount(teacherAccount.getAccount());
            project.setTName(studentRequestDto.getTeacherName());
            project.setPSource(studentRequestDto.getPSource());
            project.setPCode(studentRequestDto.getPCode());
            project.setPIntroduction(studentRequestDto.getPIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYear(studentRequestDto.getYear());
            project.setCollege(studentRequestDto.getLeaderCollege());
            project.setRecordState("已提交");
            studentDao.studentSave(project);
        }else if(projectFirst.getRecordState().equals("已保存")){
            studentDao.updateProject("已提交", studentRequestDto.getLeaderAccount(),studentRequestDto.getYear());
        }else{
            studentDao.updatePath(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),studentRequestDto.getYear());
        }
        return "提交成功";
    }

    @Override
    public Map<String,Object> edit(String leaderAccount,String year) {
        Map<String,Object> resultMap = new HashMap<>();
        Project project = studentDao.selectByLeaderAccountAndYear(leaderAccount,year);
        StudentRequestDto studentDto = new StudentRequestDto();
        if(project!=null){
            studentDto.setPName(project.getPName());
            studentDto.setPType(project.getPType());
            studentDto.setLeaderAccount(project.getSAccount());
            studentDto.setLeaderCollege(project.getCollege());
            studentDto.setLeaderName(project.getSName());
            studentDto.setMemberNum(project.getMemberNum());
            studentDto.setMemberInf(project.getMemberInf());
            Teacher teacherUserName = studentDao.getTeacherUserName(project.getTAccount());
            studentDto.setTeacherName(teacherUserName.getUserName());
            studentDto.setTeacherTitle(teacherUserName.getTitle());
            studentDto.setPSource(project.getPSource());
            studentDto.setPCode(project.getPCode());
            studentDto.setPIntroduction(project.getPIntroduction());
            resultMap.put("tableData",studentDto);
            resultMap.put("recordState",project.getRecordState());
        }else{
            resultMap.put("tableData",null);
            resultMap.put("recordState",project.getRecordState());
        }
        return resultMap;
    }

    @Override
    public List<MyProjectDto> myProject(String leaderAccount) {
        List<MyProjectDto> result = new ArrayList<>();
        List<Project> projects = studentDao.selectByLeaderAccount(leaderAccount);
        for (Project project : projects) {
            MyProjectDto myProjectDto = new MyProjectDto();
            myProjectDto.setUserName(project.getSName());
            myProjectDto.setMemberInf(project.getMemberInf());
            myProjectDto.setTeacherName(project.getTName());
            if (project != null || !project.getAvg().equals("")) {
                myProjectDto.setAvg(project.getAvg());
            } else {
                myProjectDto.setAvg("-");
            }
            result.add(myProjectDto);
        }
        return result;
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {
        LoginResponse loginResponse = new LoginResponse();
        if(loginDto.getRole().equals("学生")){
            Student student = studentDao.selectByAccount(loginDto.getAccount());
            if(loginDto.getPassword().equals(student.getPassWord())){
                loginResponse.setUserName(student.getUserName());
                loginResponse.setUserType("student");
            }else{
                loginResponse.setUserName("");
            }
        }else if(loginDto.getRole().equals("教师")){
            Teacher teacher = studentDao.getTeacherUserName(loginDto.getAccount());
            if(loginDto.getPassword().equals(teacher.getPassWord())){
                loginResponse.setUserName(teacher.getUserName());
                loginResponse.setUserType("teacher");
            }else{
                loginResponse.setUserName("");
            }
        }else if(loginDto.getRole().equals("评委")){
            Judges judges = studentDao.getJudges(loginDto.getAccount());
            if(loginDto.getPassword().equals(judges.getPassWord())){
                loginResponse.setUserName(judges.getUserName());
                loginResponse.setUserType("judges");
            }else{
                loginResponse.setUserName("");
            }
        }else{
            Admin admin = studentDao.getAdmin(loginDto.getAccount());
            if(loginDto.getPassword().equals(admin.getPassWord())){
                loginResponse.setUserName(admin.getUserName());
                loginResponse.setUserType("manager");
            }else{
                loginResponse.setUserName("");
            }
        }
        return loginResponse;
    }
}
