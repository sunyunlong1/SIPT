package com.scholarship.demo.service;

import com.scholarship.demo.model.Student;
import org.springframework.stereotype.Service;

@Service
public interface studentService {

    public Integer apply(Student student);
}
