package com.scholarship.demo.dao;

import com.scholarship.demo.model.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface studentDao {

    Integer apply(Project project);

}
