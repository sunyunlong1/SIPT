package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentDao {


    @Select({"<script> select * from project " +
            " where sAccount = #{leaderAccount} " +
            "</script>"})
    @ResultType(Project.class)
    Project selectByLeaderAccount(String leaderAccount);


    @Insert({"<script> insert into project (pName,sAccount,memberNum,memberInf,tAccount,pSource,pCode,pType,pIntroduction,pStatus,pathFirst,pathSecond,pathThird,recordState,currentProgress) " +
            " VALUES(#{p.pName},#{p.sAccount},#{p.memberNum},#{p.memberInf},#{p.tAccount},#{p.pSource},#{p.pCode},#{p.pType},#{p.pIntroduction},#{p.pStatus},#{p.pathFirst},#{p.pathSecond},#{p.pathThird},#{p.recordState},#{p.currentProgress}) " +
            "</script>"})
    boolean studentSave(@Param("p") Project project);


    @Select({"<script> " +
            "select * from teacher where userName = #{userName} " +
            "</script>"})
    @ResultType(Teacher.class)
    Teacher getTeacherAccount(String userName);



    Boolean updatePath(String pathSecond,String pathThird,String sAccount,String pStatus,String recordState);


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


    @Select({"<script> " +
            "update project " +
            "<set> recordState = #{recordState} </set> " +
            "where sAccount = #{leaderAccount} " +
            "</script>"})
    void updateProject(String recordState,String leaderAccount);

}
