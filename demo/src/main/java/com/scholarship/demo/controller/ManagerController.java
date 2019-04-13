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

import javax.servlet.http.HttpServletRequest;
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
    public String apply(@RequestBody List<ManagerDto> managerDto){
        String apply = managerService.apply(managerDto);
        return JSON.toJSONString(new Result(200,"-",apply));
    }


    @RequestMapping("/stop")
    @ResponseBody
    public String stop(@RequestBody Map<String,List<ManagerDto>> managerDto){
        String stop = managerService.stop(managerDto);
        return JSON.toJSONString(new Result(200,"-",stop));
    }

    @RequestMapping("/overview")
    @ResponseBody
    public String overview(HttpServletRequest request){
        List<OverviewResponse> overview = managerService.overview();
        return JSON.toJSONString(new Result(200,"-",overview));
    }

    @RequestMapping("/details")
    @ResponseBody
    public String details(@RequestBody OverviewResponse overview){
        List<ManagerViewProject> details = managerService.details(overview);
        return JSON.toJSONString(new Result(200,"-",details));
    }

}
