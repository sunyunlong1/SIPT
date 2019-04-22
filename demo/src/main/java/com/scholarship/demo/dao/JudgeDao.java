package com.scholarship.demo.dao;

import com.scholarship.demo.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JudgeDao {

    @Select({"<script> select * from process where year = #{year} </script>"})
    @ResultType(SiptProcess.class)
    SiptProcess selectByYear(String year);

    @Select({"<script> select * from project where jAccount = #{jAccount} and year = #{year} and tApproval != '' </script>"})
    @ResultType(Project.class)
    List<Project> selectByJidANdYear(String jAccount,String year);

    @Select({"<script> select * from process where isConduct = #{isConduct} </script>"})
    @ResultType(SiptProcess.class)
    List<SiptProcess> selectByConduct(String isConduct);

    @Select({"<script> select * from judges where account = #{account} </script>"})
    @ResultType(Judges.class)
    Judges selectById(String account);

    @Select({"<script> select * from project where college = #{college} </script>"})
    @ResultType(Project.class)
    List<Project> selectByCollege(String college);

    @Select({"<script> select * from pGrade where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    @ResultType(PGrade.class)
    PGrade selectByGId(String sId, String year,String pStatus);

    @Update({"<script> update pGrade <set> oneGrade = #{grade},oneInf = #{inf} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateOneGrade(String sId, String year,String pStatus,String grade,String inf);

    @Update({"<script> update pGrade <set> twoGrade = #{grade},twoInf = #{inf} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateTwoGrade(String sId, String year,String pStatus,String grade,String inf);

    @Update({"<script> update pGrade <set> threeGrade = #{grade},threeInf = #{inf} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateThreeGrade(String sId, String year,String pStatus,String grade,String inf);

    @Update({"<script> update pGrade <set> fourGrade = #{grade},fourInf = #{inf} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateFourGrade(String sId, String year,String pStatus,String grade,String inf);

    @Update({"<script> update pGrade <set> pgAvg = #{avg} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateAvg(String sId, String year,String pStatus,Double avg);

    @Update({"<script> update pGrade <set> oneApply = #{isApply} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateOneApply(String sId, String year,String pStatus,String isApply);

    @Update({"<script> update pGrade <set> twoApply = #{isApply} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateTwoApply(String sId, String year,String pStatus,String isApply);

    @Update({"<script> update pGrade <set> threeApply = #{isApply} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateThreeApply(String sId, String year,String pStatus,String isApply);

    @Update({"<script> update pGrade <set> fourApply = #{isApply} </set> where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    void updateFourApply(String sId, String year,String pStatus,String isApply);


















}
