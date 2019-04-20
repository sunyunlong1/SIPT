package com.scholarship.demo.dao;

import com.scholarship.demo.model.PGrade;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.SiptProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
public interface TeacherDao {

    @Select({"<script> select * from process where year = #{year} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByYear(String year);

    @Select({"<script> select * from project where tAccount = #{tAccount} and year = #{year} and tApproval = '1' </script>"})
    @ResultType(Project.class)
    List<Project> selectByTidAndYear(String tAccount,String year);

    @Select({"<script> select * from process where status = #{status} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByStatus(String status);

    @Update({"<script> update project <set> tApproval = #{pass} </set> where sName = #{sName} and year = #{year} </script>"})
    void updateTApproval(String pass,String sName,String year);


    @Select({"<script> select * from process </script>"})
    @ResultType(SiptProcess.class)
    List<SiptProcess> selectAll();


    @Select({"<script> select * from project where tAccount = #{account} and year = #{year} </script>"})
    @ResultType(Project.class)
    List<Project> selectBytIAndYear(String account,String year);

    @Select({"<script> select * from pGrade where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    @ResultType(PGrade.class)
    PGrade selectById(String sId,String year,String pStatus);


}
