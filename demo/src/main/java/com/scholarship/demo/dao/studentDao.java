package com.scholarship.demo.dao;

import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.model.Teacher;
import org.apache.ibatis.annotations.*;

@Mapper
public interface studentDao {


    @Select({"<script> select * from project " +
            " where sAccount = #{leaderAccount} " +
            "</script>"})
    @ResultType(Project.class)
    Project selectByLeaderAccount(String leaderAccount);


    @Insert({"<script> insert into project (pName,sAccount,memberNum,memberInf,tAccount,pSource,pCode,pType,pIntroduction,pStatus,pathFirst,pathSecond,pathThird,recordState) " +
            " VALUES(#{p.pName},#{p.sAccount},#{p.memberNum},#{p.memberInf},#{p.tAccount},#{p.pSource},#{p.pCode},#{p.pType},#{p.pIntroduction},#{p.pStatus},#{p.pathFirst},#{p.pathSecond},#{p.pathThird},#{p.recordState})) " +
            "</script>"})
    boolean studentSave(@Param("p") Project project);


    @Select({"<script> " +
            "select * from teacher where userName = #{userName} " +
            "</script>"})
    @ResultType(Teacher.class)
    Teacher getTeacherAccount(String userName);


    @Update({"<script> update project " +
            "<set> " +
            "<if test = 'pathSecond != ''' > " +
            "pathSecond = #{pathSecond} " +
            "</if>  " +
            "<if test = 'pathThird != ''' > " +
            "pathThird = #{pathThird} " +
            "</if> " +
            " and pStatus = #{pStatus} " +
            " and recordState = #{recordState} " +
            "</set> " +
            "where sAccount = #{sAccount} " +
            "</script>"})
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

}
