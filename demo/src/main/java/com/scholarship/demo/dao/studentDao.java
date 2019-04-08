package com.scholarship.demo.dao;

import com.scholarship.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface studentDao {

    public Integer apply(Student student);
}
