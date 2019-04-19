package com.scholarship.demo.dao;

import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.SiptProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgeDao {

    @Select({"<script> select * from process where year = #{year} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByYear(String year);

    @Select({"<script> select * from project where jAccount = #{jAccount} and year = #{year} and tApproval != '' </script>"})
    @ResultType(Project.class)
    List<Project> selectByJidANdYear(String jAccount,String year);
}
