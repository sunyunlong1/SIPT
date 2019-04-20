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

    @SelectProvider(type = findCollege.class,method = "findById")
    @ResultType(Project.class)
    List<Project> selectByCollege(String college);

    class findCollege{
        public String findById(String college){
            String sql = " select * from project ";
            if(!college.equals("-1")){
                sql+= " where college = #{college} ";
            }
            return sql;
        }
    }

    @SelectProvider(type = findProject.class,method = "findById")
    @ResultType(Project.class)
    List<Project> selectBySidYear(String college,String year);

    class findProject{
        public String findById(String college,String year){
            String sql = " select * from project where year = #{year} ";
            if(!college.equals("-1")){
                sql+= " and college = #{college} ";
            }
            return sql;
        }
    }

    @Select({"<script> select * from pGrade where sId = #{sId} and year = #{year} and pStatus = #{pStatus} </script>"})
    @ResultType(PGrade.class)
    PGrade selectByIdYStatus(String sId, String year, String pStatus);

    @Update({"<script> update pGrade <set> level = #{level} </set> where sName = #{sName} and year = #{year} and pStatus = #{pStatus}  </script>"})
    void UpdatePGradeLevel(String sName,String year,String pStatus,String level);

    @Update({"<script> update process <set> isCollect = #{isCollect} </set> where year = #{year} and status = #{status}  </script>"})
    void UpdatePGradeCollect(String year,String status,String isCollect);

    @Select({"<script> select * from process </script>"})
    @ResultType(SiptProcess.class)
    List<SiptProcess> selectProcess();

    @SelectProvider(type = selectProject.class,method = "findById")
    @ResultType(java.lang.Integer.class)
    Integer selectSumProject(String year,String college);

    class selectProject{
        public String findById(String year,String college){
            String sql = " select count(1) from project where year = #{year} ";
            if(!college.equals("-1")){
                sql+=" and college = #{college} ";
            }
            return sql;
        }
    }


    @Select({"<script> select * from project where year = #{year} </script>"})
    @ResultType(Project.class)
    List<Project> selectProjectByYear(String year);

    @Insert({"<script> insert process (year,status,startTime,endTime,isCollect) values(#{year},#{status},#{startTime},#{endTime},#{isCollect} ) </script>"})
    @ResultType(java.lang.Integer.class)
    Integer insertProcess(String year,String status,String startTime,String endTime,String isCollect);

    @Update({"<script> update process <set> status = #{status} </set> where year = #{year} </script>"})
    void updateProcess(String year,String status);
}
