package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.JudgeViewRep;
import com.scholarship.demo.api.JudgesSave;
import com.scholarship.demo.api.Key;
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
        Map<String,Object> result = judgeService.view(loginDto.getAccount());
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody Map<String,Object> map){
        String save = judgeService.save(map);
        return JSON.toJSONString(new Result(200,"-",save));
    }

    @RequestMapping("/apply")
    @ResponseBody
    public String apply(@RequestBody Map<String,Object> map){
        String save = judgeService.apply(map);
        return JSON.toJSONString(new Result(200,"-",save));
    }
}
