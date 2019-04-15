package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.LoginResponse;
import com.scholarship.demo.api.MyProjectDto;
import com.scholarship.demo.api.StudentRequestDto;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public String selectById(String leaderAccount) {
        Project project = studentDao.selectByLeaderAccount(leaderAccount);
        if(project == null){
            return "0";
        }else if(project.getPathThird()!=null || !project.getPathThird().equals("")){
            return project.getPathThird();
        }else if(project.getPathSecond()!=null || !project.getPathSecond().equals("")){
            return project.getPathSecond();
        }else{
            return project.getPathFirst();
        }
    }

    @Override
    public String studentSave(StudentRequestDto studentRequestDto) {

        //先查询是否有记录
        Project projectFirst = studentDao.selectByLeaderAccount(studentRequestDto.getLeaderAccount());
        if(projectFirst == null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            String year = sdf.format(date);

            Project project = new Project();
            project.setPName(studentRequestDto.getpName());
            project.setPType(studentRequestDto.getpType());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setMemberNum(studentRequestDto.getMemberNum());
            project.setMemberInf(studentRequestDto.getMemberInf());
            Teacher teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTeacherName());
            project.setTAccount(teacherAccount.getAccount());
            project.setPSource(studentRequestDto.getpSource());
            project.setPCode(studentRequestDto.getpCode());
            project.setPIntroduction(studentRequestDto.getpIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYears(year);
            project.setPStatus("立项");
            project.setRecordState("已保存");
            project.setCurrentProgress("待提交");
            studentDao.studentSave(project);
        }else{
            if (!studentRequestDto.getPathSecond().equals("")){
                studentDao.updatePath(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),"中期检查","中期保存");
            }else{
                studentDao.updatePath(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),"结项","结项保存");
            }
        }
        return "保存成功";
    }

    @Override
    public String studentApply(StudentRequestDto studentRequestDto) {
        //先查询是否有记录
        Project projectFirst = studentDao.selectByLeaderAccount(studentRequestDto.getLeaderAccount());
        if(projectFirst == null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            String year = sdf.format(date);

            Project project = new Project();
            project.setPName(studentRequestDto.getpName());
            project.setPType(studentRequestDto.getpType());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setMemberNum(studentRequestDto.getMemberNum());
            project.setMemberInf(studentRequestDto.getMemberInf());
            Teacher teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTeacherName());
            project.setTAccount(teacherAccount.getAccount());
            project.setPSource(studentRequestDto.getpSource());
            project.setPCode(studentRequestDto.getpCode());
            project.setPIntroduction(studentRequestDto.getpIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYears(year);
            project.setPStatus("立项");
            project.setRecordState("提交");
            project.setCurrentProgress("已提交");
            studentDao.studentSave(project);
        }else if(projectFirst.getRecordState().equals("已保存")){
            studentDao.updateProject("已提交", studentRequestDto.getLeaderAccount());
        }else{
            if (!studentRequestDto.getPathSecond().equals("")){
                studentDao.updatePath(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),"中期检查","中期提交");
            }else{
                studentDao.updatePath(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),"结项","结项提交");
            }
        }
        return "提交成功";
    }

    @Override
    public Map<String,Object> studentEdit(String leaderAccount) {
        Map<String,Object> resultMap = new HashMap<>();
        Project project = studentDao.selectByLeaderAccount(leaderAccount);
        StudentRequestDto studentDto = new StudentRequestDto();
        if(project!=null){
            studentDto.setpName(project.getPName());
            studentDto.setpType(project.getPType());
            studentDto.setLeaderAccount(project.getSAccount());
            Student student = studentDao.selectByAccount(leaderAccount);
            studentDto.setLeaderCollege(student.getCollege());
            studentDto.setLeaderName(student.getUserName());
            studentDto.setMemberNum(project.getMemberNum());
            studentDto.setMemberInf(project.getMemberInf());
            Teacher teacherUserName = studentDao.getTeacherUserName(project.getTAccount());
            studentDto.setTeacherName(teacherUserName.getUserName());
            studentDto.setTeacherTitle(teacherUserName.getTitle());
            studentDto.setpSource(project.getPSource());
            studentDto.setpCode(project.getPCode());
            studentDto.setpIntroduction(project.getPIntroduction());
            resultMap.put("tableData",studentDto);
            resultMap.put("recordState",project.getRecordState());
        }else{
            resultMap.put("tableData",null);
            resultMap.put("recordState",project.getRecordState());
        }
        return resultMap;
    }

    @Override
    public MyProjectDto myProject(String leaderAccount) {
        Project project = studentDao.selectByLeaderAccount(leaderAccount);
        MyProjectDto myProjectDto = new MyProjectDto();
        Student student = studentDao.selectByAccount(project.getSAccount());
        myProjectDto.setUserName(student.getUserName());
        myProjectDto.setMemberInf(project.getMemberInf());
        Teacher teacherUserName = studentDao.getTeacherUserName(project.getTAccount());
        myProjectDto.setTeacherName(teacherUserName.getUserName());
        myProjectDto.setPStatus(project.getPStatus());
        myProjectDto.setCurrentProgress(project.getCurrentProgress());
        if(project!= null || !project.getAvg().equals("")){
            myProjectDto.setAvg(project.getAvg());
        }else{
            myProjectDto.setAvg("-");
        }
        return myProjectDto;
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

    @Override
    public LoginResponse exit(LoginDto loginDto) {
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
                loginResponse.setUserType("admin");
            }else{
                loginResponse.setUserName("");
            }
        }
        return loginResponse;
    }
}
