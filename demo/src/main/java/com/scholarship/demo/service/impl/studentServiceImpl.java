package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.MyProjectDto;
import com.scholarship.demo.api.StudentRequestDto;
import com.scholarship.demo.dao.studentDao;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.Teacher;
import com.scholarship.demo.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class studentServiceImpl implements studentService {

    @Autowired
    private studentDao studentDao;


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
            project.setPName(studentRequestDto.getPName());
            project.setPType(studentRequestDto.getPtype());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setMenberNum(studentRequestDto.getMemberNum());
            project.setMenberInf(studentRequestDto.getMemberInf());
            Teacher teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTeacherName());
            project.setTAccount(teacherAccount.getAccount());
            project.setPSource(studentRequestDto.getPSource());
            project.setPCode(studentRequestDto.getPCode());
            project.setPIntroduction(studentRequestDto.getPIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYears(year);
            project.setPStatus("立项");
            project.setRecordState("保存");
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
            project.setPName(studentRequestDto.getPName());
            project.setPType(studentRequestDto.getPtype());
            project.setSAccount(studentRequestDto.getLeaderAccount());
            project.setMenberNum(studentRequestDto.getMemberNum());
            project.setMenberInf(studentRequestDto.getMemberInf());
            Teacher teacherAccount = studentDao.getTeacherAccount(studentRequestDto.getTeacherName());
            project.setTAccount(teacherAccount.getAccount());
            project.setPSource(studentRequestDto.getPSource());
            project.setPCode(studentRequestDto.getPCode());
            project.setPIntroduction(studentRequestDto.getPIntroduction());
            project.setPathFirst(studentRequestDto.getPathFirst());
            project.setPathSecond(studentRequestDto.getPathSecond());
            project.setPathThird(studentRequestDto.getPathThird());
            project.setYears(year);
            project.setPStatus("立项");
            project.setRecordState("提交");
            studentDao.studentSave(project);
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
            studentDto.setPName(project.getPName());
            studentDto.setPtype(project.getPType());
            studentDto.setLeaderAccount(project.getSAccount());
            Student student = studentDao.selectByAccount(leaderAccount);
            studentDto.setLeaderCollege(student.getCollege());
            studentDto.setLeaderName(student.getUserName());
            studentDto.setMemberNum(project.getMenberNum());
            studentDto.setMemberInf(project.getMenberInf());
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
    public MyProjectDto myProject(String leaderAccount) {
        Project project = studentDao.selectByLeaderAccount(leaderAccount);
        MyProjectDto myProjectDto = new MyProjectDto();
        Student student = studentDao.selectByAccount(project.getSAccount());
        myProjectDto.setUserName(student.getUserName());
        myProjectDto.setMemberInf(project.getMenberInf());
        Teacher teacherUserName = studentDao.getTeacherUserName(project.getTAccount());
        myProjectDto.setTeacherName(teacherUserName.getUserName());
        myProjectDto.setPStatus(project.getPStatus());
        myProjectDto.setZhuangtai("?????");
        if(!project.getAvg().equals("")){
            myProjectDto.setAvg(project.getAvg());
        }else{
            myProjectDto.setAvg("-");
        }
        return myProjectDto;
    }
}
