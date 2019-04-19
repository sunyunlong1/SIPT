package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.TeacherAppRep;
import com.scholarship.demo.api.TeacherApprove;
import com.scholarship.demo.response.Result;
import com.scholarship.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/pApproval")
    @ResponseBody
    public String pApproval(@RequestBody LoginDto loginDto){
        List<TeacherAppRep> result = teacherService.pApproval(loginDto.getAccount(), loginDto.getYear());
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/approve")
    @ResponseBody
    public String approve(@RequestBody TeacherApprove teacherApprove){
        String result = teacherService.approve(teacherApprove);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/myProject")
    @ResponseBody
    public String myProject(@RequestBody LoginDto loginDto){
        return "";
    }
}
