package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao {


    @Select({"<script> select * from project " +
            " where sAccount = #{leaderAccount} and year = #{year} and recordState != #{recordState} " +
            "</script>"})
    @ResultType(Project.class)
    Project selectByLeaderAccountAndYear(String leaderAccount,String year,String recordState);


    @Select({"<script> select * from project " +
            " where sAccount = #{leaderAccount} and year = #{year} " +
            "</script>"})
    @ResultType(Project.class)
    Project selectByAccountAndYear(String leaderAccount,String year);

    @Select({"<script> select * from project " +
            " where sAccount = #{leaderAccount} " +
            "</script>"})
    @ResultType(Project.class)
    List<Project> selectByLeaderAccount(String leaderAccount);

    @Insert({"<script> insert into project (year,pName,sAccount,sName,memberNum,memberInf,tAccount,tName,pSource,pCode,pType,pIntroduction,pathFirst,firstName,pathSecond,secondName,pathThird,thirdName,recordState,college,trecordState) " +
            " VALUES(#{p.year},#{p.pName},#{p.sAccount},#{p.sName},#{p.memberNum},#{p.memberInf},#{p.tAccount},#{p.tName},#{p.pSource},#{p.pCode},#{p.pType},#{p.pIntroduction},#{p.pathFirst},#{p.firstName},#{p.pathSecond},#{p.secondName},#{p.pathThird},#{p.thirdName},#{p.recordState},#{p.college},#{p.trecordState}) " +
            "</script>"})
    @ResultType(java.lang.Boolean.class)
    boolean studentSave(@Param("p") Project project);

    @Update({"<script> update project <set> pName = #{p.pName},sName = #{p.sName},memberNum = #{p.memberNum},memberInf = #{p.memberInf},tAccount = #{p.tAccount},tName = #{p.tName},pSource = #{p.pSource},pCode = #{p.pCode},pType = #{p.pType},pIntroduction = #{p.pIntroduction},pathFirst = #{p.pathFirst},firstName = #{p.firstName},pathSecond = #{p.pathSecond},secondName = #{p.secondName},pathThird = #{p.pathThird},thirdName = #{p.thirdName},recordState = #{p.recordState},college = #{p.college} </set> where sAccount = #{account} and year = #{year} </script>"})
    void updateSave(@Param("p") Project project,String account,String year);


    @Select({"<script> " +
            "select * from teacher where account = #{tId} " +
            "</script>"})
    @ResultType(Teacher.class)
    Teacher getTeacherAccount(String tId);



    @Update({"<script> " +
            "update project " +
            "<set> " +
            " pathSecond = #{pathSecond},pathThird = #{pathThird} " +
            "</set> " +
            " where sAccount = #{sAccount} and year = #{year} " +
            "</script>"})
    void updatePathA(String pathSecond,String pathThird,String sAccount,String year);


    @Select({"<script> " +
            "select * from student where account = #{account} " +
            "</script>"})
    @ResultType(Student.class)
    Student selectByAccount(String account);

    @Select({"<script> " +
            "select * from teacher where account = #{account} " +
            "</script>"})
    @ResultType(Teacher.class)
    Teacher getTeacherUserName(String account);


    @Select({"<script> " +
            " select * from judges where account = #{account} " +
            " </script>"})
    @ResultType(Judges.class)
    Judges getJudges(String account);


    @Select({"<script> select * from admin where account = #{account} </script>"})
    @ResultType(Admin.class)
    Admin getAdmin(String account);


    @Update({"<script> " +
            "update project " +
            "<set> recordState = #{recordState} </set> " +
            "where sAccount = #{leaderAccount} and year = #{year}" +
            "</script>"})
    void updateProject(String recordState,String leaderAccount,String year);


    @Select({"<script> select * from process where year = #{year} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByYear(String year);

    @Select({"<script> select * from process where year = #{year} and isConduct = #{isConduct} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByYearAndConduct(String year,String isConduct);

    @Select({"<script> select * from process where isConduct = #{isConduct} </script>"})
    @ResultType(SiptProcess.class)
    List<SiptProcess> selectByConduct(String isConduct);

    @Insert({"<script> insert into pGrade(sId,sName,year,pStatus) values(#{sId},#{sName},#{year},#{pStatus}) </script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertpGrade(String sId,String sName,String year,String pStatus);

    @Select({"<script> select * from pGrade where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    @ResultType(PGrade.class)
    PGrade selectGrade(String sId,String year,String pStatus);
}
