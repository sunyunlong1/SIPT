package com.scholarship.demo.service.impl;

import com.scholarship.demo.dao.studentDao;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class studentServiceImpl implements studentService {

    @Autowired
    private studentDao studentDao;

    @Override
    public Integer apply(Student student) {
        Date date = new Date();
        student.setLastTime(date);
        Integer apply = studentDao.apply(student);
        return apply;
    }
}
