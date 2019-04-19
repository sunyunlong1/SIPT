package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao {


    @Select({"<script> select * from project " +
            " where sAccount = #{leaderAccount} and year = #{year} " +
            "</script>"})
    @ResultType(Project.class)
    Project selectByLeaderAccountAndYear(String leaderAccount,String year);

    @Select({"<script> select * from project " +
            " where sAccount = #{leaderAccount} " +
            "</script>"})
    @ResultType(Project.class)
    List<Project> selectByLeaderAccount(String leaderAccount);

    @Insert({"<script> insert into project (pName,sAccount,memberNum,memberInf,tAccount,pSource,pCode,pType,pIntroduction,pathFirst,pathSecond,pathThird,recordState,college) " +
            " VALUES(#{p.pName},#{p.sAccount},#{p.memberNum},#{p.memberInf},#{p.tAccount},#{p.pSource},#{p.pCode},#{p.pType},#{p.pIntroduction},#{p.pathFirst},#{p.pathSecond},#{p.pathThird},#{p.recordState},#{college}) " +
            "</script>"})
    @ResultType(java.lang.Boolean.class)
    boolean studentSave(@Param("p") Project project);


    @Select({"<script> " +
            "select * from teacher where userName = #{userName} " +
            "</script>"})
    @ResultType(Teacher.class)
    Teacher getTeacherAccount(String userName);


    @Update({"<script> update project <set> pathSecond = #{pathSecond},pathThird = #{pathThird} <set> where sAccount = #{sAccount} and year = #{year} </script>"})
    void updatePath(String pathSecond,String pathThird,String sAccount,String year);


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


}
