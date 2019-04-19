package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.JudgeViewRep;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.response.Result;
import com.scholarship.demo.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/judge")
public class JudgeController {

    @Autowired
    JudgeService judgeService;

    @RequestMapping("/view")
    @ResponseBody
    public String view(@RequestBody LoginDto loginDto){
        Map<String, List<JudgeViewRep>> result = judgeService.view(loginDto.getAccount(), loginDto.getYear());
        return JSON.toJSONString(new Result(200,"-",result));
    }


}
