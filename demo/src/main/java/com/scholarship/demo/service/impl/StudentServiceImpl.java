package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.StudentDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public CurrentProcessRep currentPorcess(String account) {
        CurrentProcessRep result = new CurrentProcessRep();
        //SiptProcess siptProcess = studentDao.selectByYear(year);
        //Map<String,KeyUser> map = new HashMap<>();
        List<KeyUser> keyUserList = new ArrayList<>();
        List<SiptProcess> siptProcessList = studentDao.selectByConduct("流程中");
        if(siptProcessList.size() == 0){
            return null;
        }else if(siptProcessList.size() == 1){
            KeyUser keyUser = new KeyUser();
            result.setProcessName(siptProcessList.get(0).getYear()+" SIPT "+siptProcessList.get(0).getStatus());
            result.setKey(account+"#"+siptProcessList.get(0).getYear());
            result.setStartTime(siptProcessList.get(0).getStartTime());
            result.setEndTime(siptProcessList.get(0).getEndTime());
            result.setIsCollect(siptProcessList.get(0).getIsCollect());
            Project project = studentDao.selectByLeaderAccountAndYear(account, siptProcessList.get(0).getYear(),"已保存");
            if(project != null){
                keyUser.setKey(account+"#"+siptProcessList.get(0).getYear());
                keyUser.setFileName(project.getPName());
                keyUser.setStatus(siptProcessList.get(0).getStatus());
                keyUserList.add(keyUser);
                result.setProjectList(keyUserList);
            }
        }else {

            List<Project> projects = studentDao.selectByLeaderAccount(account);

            Integer year = Integer.valueOf(siptProcessList.get(0).getYear());
            Integer nYear = Integer.valueOf(siptProcessList.get(1).getYear());
            if (year > nYear) {
                KeyUser keyUser = new KeyUser();
                KeyUser nkeyUser = new KeyUser();

                Project project = studentDao.selectByLeaderAccountAndYear(account, year.toString(),"已保存");
                Project nProject = studentDao.selectByLeaderAccountAndYear(account, nYear.toString(),"已保存");
                if(project != null){
                    keyUser.setStatus(siptProcessList.get(0).getStatus());
                    keyUser.setFileName(project.getPName());
                    keyUser.setKey(account+"#"+year);
                    keyUserList.add(keyUser);
                }
                if(nProject != null){
                    nkeyUser.setStatus(siptProcessList.get(1).getStatus());
                    nkeyUser.setFileName(nProject.getPName());
                    nkeyUser.setKey(account+"#"+nYear);
                    keyUserList.add(nkeyUser);
                }
                result.setProjectList(keyUserList);
                result.setProcessName(siptProcessList.get(0) + " SIPT " + siptProcessList.get(0).getStatus() + "/" + siptProcessList.get(1) + " SIPT " + siptProcessList.get(1).getStatus());
                result.setIsCollect(siptProcessList.get(0).getIsCollect());
                result.setStartTime(siptProcessList.get(0).getStartTime());
                result.setEndTime(siptProcessList.get(0).getEndTime());
            } else {
                KeyUser keyUser = new KeyUser();
                KeyUser nkeyUser = new KeyUser();

                Project project = studentDao.selectByLeaderAccountAndYear(account, year.toString(),"已保存");
                Project nProject = studentDao.selectByLeaderAccountAndYear(account, nYear.toString(),"已保存");
                if(project != null){
                    nkeyUser.setStatus(siptProcessList.get(0).getStatus());
                    nkeyUser.setFileName(project.getPName());
                    nkeyUser.setKey(account+"#"+year);
                    keyUserList.add(nkeyUser);
                }
                if(nProject != null){
                    keyUser.setStatus(siptProcessList.get(1).getStatus());
                    keyUser.setFileName(nProject.getPName());
                    keyUser.setKey(account+"#"+nYear);
                    keyUserList.add(keyUser);
                }
                result.setProjectList(keyUserList);

                result.setProcessName(siptProcessList.get(1) + " SIPT " + siptProcessList.get(1).getStatus() + "/" + siptProcessList.get(0) + " SIPT " + siptProcessList.get(0).getStatus());
                result.setIsCollect(siptProcessList.get(1).getIsCollect());
                result.setStartTime(siptProcessList.get(1).getStartTime());
                result.setEndTime(siptProcessList.get(1).getEndTime());
            }
        }
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
        String[] split = studentRequestDto.getKey().split("#");
        //先查询是否有记录
        Project projectFirst = studentDao.selectByLeaderAccountAndYear(studentRequestDto.getLeaderAccount(),split[1],"已保存");
        if(projectFirst == null){
            Project project = new Project();
            project.setPName(studentRequestDto.getName());
            project.setPType(studentRequestDto.getType());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setSName(studentRequestDto.getLeaderName());
            project.setMemberNum(studentRequestDto.getMemberNum());
            project.setMemberInf(studentRequestDto.getMemberInf());
            Teacher  teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTAccount());
            if (teacherAccount != null) {
                project.setTAccount(studentRequestDto.getTAccount());
                project.setTName(studentRequestDto.getTeacherName());
            }
            project.setPSource(studentRequestDto.getSource());
            project.setPCode(studentRequestDto.getCode());
            project.setPIntroduction(studentRequestDto.getIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYear(split[1]);
            project.setCollege(studentRequestDto.getLeaderCollege());
            project.setRecordState("已保存");
            studentDao.studentSave(project);
        }else{
            studentDao.updatePathA(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),split[1]);
        }
        return "保存成功";
    }

    @Override
    public String studentApply(StudentRequestDto studentRequestDto) {
        String[] split = studentRequestDto.getKey().split("#");
        SiptProcess siptProcess = studentDao.selectByYear(split[1]);
        //先查询是否有记录
        Project projectFirst = studentDao.selectByLeaderAccountAndYear(split[0],split[1],"已保存");
        if(projectFirst == null){
            Project project = new Project();
            project.setPName(studentRequestDto.getName());
            project.setPType(studentRequestDto.getType());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setSName(studentRequestDto.getLeaderName());
            project.setMemberNum(studentRequestDto.getMemberNum());
            project.setMemberInf(studentRequestDto.getMemberInf());
            Teacher  teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTAccount());
            if (teacherAccount == null) {
                return "指导教师不存在";
            }else{
                project.setTAccount(studentRequestDto.getTAccount());
                project.setTName(studentRequestDto.getTeacherName());
            }
            project.setPSource(studentRequestDto.getSource());
            project.setPCode(studentRequestDto.getCode());
            project.setPIntroduction(studentRequestDto.getIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYear(split[1]);
            project.setCollege(studentRequestDto.getLeaderCollege());
            project.setRecordState("已提交");
            studentDao.studentSave(project);


            studentDao.insertpGrade(projectFirst.getSAccount(),projectFirst.getSName(),projectFirst.getYear(),siptProcess.getStatus());

        }else if(projectFirst.getRecordState().equals("已保存")){
            studentDao.updateProject("已提交", studentRequestDto.getLeaderAccount(),split[1]);
            studentDao.insertpGrade(projectFirst.getSAccount(),projectFirst.getSName(),projectFirst.getYear(),siptProcess.getStatus());
        }else{
            studentDao.updatePathA(studentRequestDto.getPathSecond(),studentRequestDto.getPathThird(),studentRequestDto.getLeaderAccount(),split[1]);
            studentDao.insertpGrade(projectFirst.getSAccount(),projectFirst.getSName(),projectFirst.getYear(),siptProcess.getStatus());
        }
        return "提交成功";
    }

    @Override
    public Map<String,Object> edit(Key key) {
        String[] split = key.getKey().split("#");
        Map<String,Object> resultMap = new HashMap<>();
        Project project = studentDao.selectByAccountAndYear(split[0],split[1]);
        StudentRequestDto studentDto = new StudentRequestDto();
        if(project!=null){
            studentDto.setName(project.getPName());
            studentDto.setType(project.getPType());
            studentDto.setLeaderAccount(project.getSAccount());
            studentDto.setLeaderCollege(project.getCollege());
            studentDto.setLeaderName(project.getSName());
            studentDto.setMemberNum(project.getMemberNum());
            studentDto.setTAccount(project.getTAccount());
            studentDto.setMemberInf(project.getMemberInf());
            Teacher teacherUserName = studentDao.getTeacherUserName(project.getTAccount());
            if(teacherUserName == null){
                studentDto.setTeacherName(teacherUserName.getUserName());
                studentDto.setTeacherTitle(teacherUserName.getTitle());
            }else{
                studentDto.setTeacherName(project.getTName());
                studentDto.setTeacherTitle("");
            }
            studentDto.setSource(project.getPSource());
            studentDto.setCode(project.getPCode());
            studentDto.setIntroduction(project.getPIntroduction());
            studentDto.setPathFirst(project.getPathFirst());
            studentDto.setPathSecond(project.getPathSecond());
            studentDto.setPathThird(project.getPathThird());
            studentDto.setKey(key.getKey());
            resultMap.put("tableData",studentDto);
            resultMap.put("recordState",project.getRecordState());
        }else{
            resultMap.put("tableData",null);
            resultMap.put("recordState",null);
        }
        return resultMap;
    }

    @Override
    public List<MyProjectDto> myProject(String leaderAccount) {
        List<MyProjectDto> result = new ArrayList<>();
        List<Project> projects = studentDao.selectByLeaderAccount(leaderAccount);
        for (Project project : projects) {
            MyProjectDto myProjectDto = new MyProjectDto();
            myProjectDto.setPName(project.getPName());
            myProjectDto.setKey(leaderAccount+"#"+project.getYear());
            myProjectDto.setUserName(project.getSName());
            myProjectDto.setMemberInf(project.getMemberInf());
            myProjectDto.setTeacherName(project.getTName());
            myProjectDto.setRecordStatus(project.getRecordState());
            myProjectDto.setYear(project.getYear());
            SiptProcess siptProcess = studentDao.selectByYear(project.getYear());
            myProjectDto.setPStatus(siptProcess.getStatus());
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
                loginResponse.setAccount(student.getAccount());
            }else{
                loginResponse.setUserName("");
            }
        }else if(loginDto.getRole().equals("教师")){
            Teacher teacher = studentDao.getTeacherUserName(loginDto.getAccount());
            if(loginDto.getPassword().equals(teacher.getPassWord())){
                loginResponse.setUserName(teacher.getUserName());
                loginResponse.setUserType("teacher");
                loginResponse.setAccount(teacher.getAccount());
            }else{
                loginResponse.setUserName("");
            }
        }else if(loginDto.getRole().equals("评委")){
            Judges judges = studentDao.getJudges(loginDto.getAccount());
            if(loginDto.getPassword().equals(judges.getPassWord())){
                loginResponse.setUserName(judges.getUserName());
                loginResponse.setUserType("judges");
                loginResponse.setAccount(judges.getAccount());
            }else{
                loginResponse.setUserName("");
            }
        }else{
            Admin admin = studentDao.getAdmin(loginDto.getAccount());
            if(loginDto.getPassword().equals(admin.getPassWord())){
                loginResponse.setUserName(admin.getUserName());
                loginResponse.setUserType("manager");
                loginResponse.setAccount(admin.getAccount());
            }else{
                loginResponse.setUserName("");
            }
        }
        return loginResponse;
    }
}
