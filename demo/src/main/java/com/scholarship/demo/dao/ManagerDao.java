package com.scholarship.demo.dao;

import com.scholarship.demo.api.ManagerDto;
import com.scholarship.demo.api.UnifiedTable;
import com.scholarship.demo.model.PingshenGrade;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ManagerDao {

    @Select({"<script> select s.college as college,s.userName as userName,t.userName as tName,p.pType as pType,pg.oneGrade as oneGrade,pg.twoGrade as twoGrade,pg.threeGrade as threeGrade,pg.fourGrade as fourGrade,pg.pgAvg as pgAvg,pg.level as level " +
            " from project p " +
            " INNER JOIN pingshenGrade pg " +
            " on p.sAccount = pg.sId and p.pStatus = pg.pStatus " +
            " INNER JOIN student s on p.sAccount = s.account " +
            " INNER JOIN teacher t on p.tAccount = t.account " +
            " where p.pStatus = #{pStatus} " +
            "</script>"})
    @ResultType(ManagerDto.class)
    List<ManagerDto> currentProcess(String pStatus);


    @Select({"<script> select p.pStatus as currentProcess,a.level as level from project p,admin a  " +
            "where a.account = #{account} order BY p.pStatus limit 1 </script>"})
    @ResultType(UnifiedTable.class)
    UnifiedTable managerUnifiedTable(String account);


    @Select({"<script> " +
            "select * from project where pStatus = #{pStatus} order by years limit 1 " +
            "</script>"})
    @ResultType(Project.class)
    Project selectByStatus(String pStatus);


    @Select({"<script> select * from student where userName = #{userName} </script>"})
    @ResultType(Student.class)
    Student selecyByUserName(String userName);


    @Update({"<script> " +
            "update pingshenGrade " +
            "<set> level = #{level} </set> " +
            " where sId = #{sId} and pStatus = #{pStatus} " +
            "</script>"})
    void updateById(String sId,String pStatus,String level);

    @Update({"<script> " +
            "update project " +
            "<set> isCollect = 'stop' </set> " +
            " where sAccount = #{sAccount} and pStatus = #{pStatus} " +
            "</script>"})
    void stop(String pStatus,String sAccount);

    @Select({"<script> select sum(1) from project where years = #{years} </script>"})
    @ResultType(java.lang.Integer.class)
    Integer sum(String years);

    @Select({"<script> select pStatus from project where years = #{years} ORDER BY pStatus limit 1  </script>"})
    @ResultType(java.lang.String.class)
    String getStatus(String years);

    @Select({"<script> select * from project where years = #{years} </script>"})
    @ResultType(Project.class)
    List<Project> selectByYears(String years);


    @Update({"<script> " +
            "update project " +
            "<set> " +
            "pStatus = #{pStatus} " +
            "</set> " +
            "where years = #{years} " +
            "</script>"})
    void newAndEditProcess(String pStatus,String years);
}
