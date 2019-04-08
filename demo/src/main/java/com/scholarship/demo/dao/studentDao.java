package com.scholarship.demo.dao;

import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface studentDao {

    Integer apply(Student student);
}
