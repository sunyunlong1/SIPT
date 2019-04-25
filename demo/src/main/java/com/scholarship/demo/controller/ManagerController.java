package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.*;
import com.scholarship.demo.response.Result;
import com.scholarship.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping("/currentProcess")
    @ResponseBody
    public String currentProcess(@RequestBody LoginDto leaderAccount){

        ManagerTableDto managerTableDto = managerService.currentProcess(leaderAccount.getAccount());
        if(managerTableDto == null){
            return JSON.toJSONString(new Result(200,"没有数据","null"));
        }else{
            return JSON.toJSONString(new Result(200,"-",managerTableDto));
        }
    }

    @RequestMapping("/apply")
    @ResponseBody
    public String apply(@RequestBody Map<String,List<Key>> keyMap){
        String apply = managerService.apply(keyMap);
        return JSON.toJSONString(new Result(200,"-",apply));
    }


    @RequestMapping("/stop")
    @ResponseBody
    public String stop(@RequestBody OverviewResponse overview){
        String stop = managerService.stop(overview.getKey());
        if(stop.equals("success")){
            stop = "成功改为停止收取状态";
            return JSON.toJSONString(new Result(200,"-",stop));
        }else{
            stop = "当前已为停止收取状态，不可重复点击";
            return JSON.toJSONString(new Result(405,"-",stop));

        }
    }

    @RequestMapping("/overview")
    @ResponseBody
    public String overview(@RequestBody LoginDto leaderAccount){
        OverViewDto overview = managerService.overview(leaderAccount.getAccount());
        return JSON.toJSONString(new Result(200,"-",overview));
    }

    @RequestMapping("/details")
    @ResponseBody
    public String details(@RequestBody Key key){
        List<ManagerViewProject> details = managerService.details(key);
        return JSON.toJSONString(new Result(200,"-",details));
    }

    @RequestMapping("/newAndEditProcess")
    @ResponseBody
    public String newAndEditProcess(@RequestBody NewProcessDto newProcessDto){
        String result = managerService.newAndEditProcess(newProcessDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }
}
