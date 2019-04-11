package com.scholarship.demo.service;

import com.scholarship.demo.model.Project;
import org.springframework.stereotype.Service;

@Service
public interface studentService {

    public Integer apply(Project project);
}
