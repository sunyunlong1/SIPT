package com.scholarship.demo.service.impl;

import com.scholarship.demo.dao.studentDao;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class studentServiceImpl implements studentService {

    @Autowired
    private studentDao studentDao;

    @Override
    public Integer apply(Project project) {

        Date date = new Date();
        project.setLastTime(date);
        Integer apply = studentDao.apply(project);
        return apply;
    }
}
