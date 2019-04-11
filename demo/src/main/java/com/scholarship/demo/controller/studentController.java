package com.scholarship.demo.controller;

import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.Student;
import com.scholarship.demo.response.Result;
import com.scholarship.demo.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/student")
@Controller
public class studentController {

    @Autowired
    private studentService studentService;

    @RequestMapping("/apply")
    @ResponseBody
    public String apply(@RequestBody Project project){
        Integer apply = studentService.apply(project);

        if(apply!=0){
            return new Result(200,"-",apply.toString()).toString();
        }else{
            return new Result(405,"提交失败","-").toString();
        }
    }

//    @RequestMapping("/save")
//    @ResponseBody
//    public String save(@RequestBody Student student,String )
}
