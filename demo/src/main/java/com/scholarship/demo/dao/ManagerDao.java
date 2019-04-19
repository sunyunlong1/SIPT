package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManagerDao {


    @Select({"<script> select * from admin where account = #{account} </script>"})
    @ResultType(Admin.class)
    Admin selectById(String account);

    @Select({"<script> select * from process where year = #{year} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByYear(String year);

    @Select({"<script> select * from student " +
            "<if test = 'college != '-1''> " +
            " where college = #{college} " +
            "</if> " +
            "</script>"})
    @ResultType(Student.class)
    List<Student> selectByCollege(String college);

    @Select({"<script> select * from project where sAccount = #{sAccount} and year = #{year} </script>"})
    @ResultType(Project.class)
    Project selectBySidYear(String sAccount,String year);


    @Select({"<script> select * from pGrade where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    @ResultType(PGrade.class)
    PGrade selectByIdYStatus(String sId, String year, String pStatus);

    @Update({"<script> update pGrade <set> level = #{level} </set> where sName = #{sName} and year = #{year} and pStatus = #{pStatus}  </script>"})
    void UpdatePGradeLevel(String sName,String year,String pStatus,String level);

    @Update({"<script> update pGrade <set> isCollect = #{isCollect} </set> where sName = #{sName} and year = #{year} and pStatus = #{pStatus}  </script>"})
    void UpdatePGradeCollect(String sName,String year,String pStatus,String isCollect);

    @Select({"<script> select * from process </script>"})
    @ResultType(SiptProcess.class)
    List<SiptProcess> selectProcess();

    @Select({"<script> select count(1) from project where year = #{year} <if test = 'college != '-1''> and college = #{college} </if> </script>>"})
    @ResultType(java.lang.Integer.class)
    Integer selectSumProject(String year,String college);

    @Select({"<script> select * from project where year = #{year} </script>"})
    @ResultType(Project.class)
    List<Project> selectProjectByYear(String year);

    @Insert({"<script> insert process (year,status,startTime,endTime,isCollect) values(#{year},#{status},#{startTime},#{endTime},#{isCollect} ) </script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertProcess(String year,String status,String startTime,String endTime,String isCollect);

    @Update({"<script> update process <set> status = #{status} </set> where year = #{year} </script>"})
    void updateProcess(String year,String status);
}
