package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.JudgeViewRep;
import com.scholarship.demo.dao.JudgeDao;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.SiptProcess;
import com.scholarship.demo.service.JudgeService;
import com.sun.deploy.panel.JHighDPITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    JudgeDao judgeDao;

    @Override
    public Map<String, List<JudgeViewRep>> view(String jAccount, String year) {
        Map<String, List<JudgeViewRep>> result = new HashMap<>();
        List<JudgeViewRep>  resultList = new ArrayList<>();
        List<JudgeViewRep>  lastResultList = new ArrayList<>();
        List<JudgeViewRep>  nextResultList = new ArrayList<>();


        SiptProcess siptProcess = judgeDao.selectByYear(year);

        List<Project> projects = judgeDao.selectByJidANdYear(jAccount, year);
        for(Project project : projects){
            JudgeViewRep judgeViewRep = new JudgeViewRep();
            judgeViewRep.setPName(project.getPName());
            judgeViewRep.setPType(project.getPType());
            resultList.add(judgeViewRep);
        }

        if(siptProcess.getStatus().equals("立项")){
            result.put(siptProcess.getYear()+siptProcess.getStatus(),resultList);
        }else if(siptProcess.getStatus().equals("中期检查")){
            Integer lastYear = Integer.valueOf(year)-1;
            result.put(siptProcess.getYear()+siptProcess.getStatus(),resultList);
            SiptProcess lastSiptProcess = judgeDao.selectByYear(lastYear.toString());
            List<Project> lastProjects = judgeDao.selectByJidANdYear(jAccount, lastYear.toString());
            for(Project project : lastProjects){
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                judgeViewRep.setPName(project.getPName());
                judgeViewRep.setPType(project.getPType());
                resultList.add(judgeViewRep);
            }
            result.put(lastSiptProcess.getYear()+lastSiptProcess.getStatus(),lastResultList);
        }else if(siptProcess.getStatus().equals("结题")){
            Integer nextYear = Integer.valueOf(year)+1;
            SiptProcess nextSiptProcess = judgeDao.selectByYear(nextYear.toString());
            List<Project> nextProjects = judgeDao.selectByJidANdYear(jAccount, nextYear.toString());
            for(Project project : nextProjects){
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                judgeViewRep.setPName(project.getPName());
                judgeViewRep.setPType(project.getPType());
                resultList.add(judgeViewRep);
            }
            result.put(nextSiptProcess.getYear()+nextSiptProcess.getStatus(),lastResultList);
            result.put(siptProcess.getYear()+siptProcess.getStatus(),resultList);
        }
        return result;
    }
}
