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

    @Select({"<script> select * from project where tAccount = #{tAccount} and year = #{year} and tApproval = '' </script>"})
    @ResultType(Project.class)
    List<Project> selectByTidAndYear(String tAccount,String year);

    @Select({"<script> select * from process where year = #{year} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByStatus(String year);

    @Update({"<script> update project <set> tApproval = #{pass},trecordState = #{trecordState} </set> where sAccount = #{sAccount} and year = #{year} </script>"})
    void updateTApproval(String pass,String sAccount,String year,String trecordState);


    @Select({"<script> select * from process </script>"})
    @ResultType(SiptProcess.class)
    List<SiptProcess> selectAll();


    @Select({"<script> select * from project where tAccount = #{account} and year = #{year} and trecordState = #{trecordState} </script>"})
    @ResultType(Project.class)
    List<Project> selectBytIAndYear(String account,String year,String trecordState);

    @Select({"<script> select * from pGrade where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    @ResultType(PGrade.class)
    PGrade selectById(String sId,String year,String pStatus);

    @Select({"<script> select * from process where isConduct = #{isConduct} </script>"})
    @ResultType(SiptProcess.class)
    List<SiptProcess> selectByConduct(String isConduct);


}
