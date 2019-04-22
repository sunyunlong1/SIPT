package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.JudgeViewRep;
import com.scholarship.demo.api.JudgesSave;
import com.scholarship.demo.dao.JudgeDao;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.PGrade;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.SiptProcess;
import com.scholarship.demo.service.JudgeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.*;

@Repository
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    JudgeDao judgeDao;

    @Override
    public Map<String,Object> view(String jAccount) {
        Map<String,Object> resultMap = new HashMap<>();

        Map<String, Object> result = new HashMap<>();
        String oStatus = "";
        String tStatus = "";
        Judges judges = judgeDao.selectById(jAccount);
        List<SiptProcess> siptProcessList = judgeDao.selectByConduct("流程中");
        for (SiptProcess siptProcess : siptProcessList) {
            List<JudgeViewRep> resultList = new ArrayList<>();
            List<Project> projects = judgeDao.selectByCollege(judges.getCollege());
            for (Project project : projects) {
                PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                if(judges.getNumber().equals("one") && pGrade.getOneGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }else if(judges.getNumber().equals("two") && pGrade.getTwoGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }else if(judges.getNumber().equals("three") && pGrade.getThreeGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }else if(judges.getNumber().equals("four") && pGrade.getFourGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }
                resultList.add(judgeViewRep);
            }
            result.put(siptProcess.getYear() + siptProcess.getStatus(), resultList);
        }
        resultMap.put("待审批",result);
        //已审批
        Map<String, Object> Yresult = new HashMap<>();

        for (SiptProcess siptProcess : siptProcessList) {
            List<JudgeViewRep> resultList = new ArrayList<>();
            List<Project> projects = judgeDao.selectByCollege(judges.getCollege());
            for (Project project : projects) {
                PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                if(judges.getNumber().equals("one") && pGrade.getOneGrade() != -1){
                    tStatus = pGrade.getOneApply();
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getOneGrade()));
                    judgeViewRep.setInf(pGrade.getOneInf());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }else if(judges.getNumber().equals("two") && pGrade.getTwoGrade() != -1){
                    tStatus = pGrade.getTwoApply();

                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getTwoGrade()));
                    judgeViewRep.setInf(pGrade.getTwoInf());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }else if(judges.getNumber().equals("three") && pGrade.getThreeGrade() != -1){
                    tStatus = pGrade.getThreeApply();

                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getThreeGrade()));
                    judgeViewRep.setInf(pGrade.getThreeInf());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }else if(judges.getNumber().equals("four") && pGrade.getFourGrade() != -1){
                    tStatus = pGrade.getFourApply();


                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getFourGrade()));
                    judgeViewRep.setInf(pGrade.getFourInf());
                    judgeViewRep.setKey(project.getYear() + "#" + siptProcess.getStatus() + "#" + project.getSAccount());
                }
                resultList.add(judgeViewRep);
            }
            Yresult.put(siptProcess.getYear() + siptProcess.getStatus(), resultList);
            Yresult.put("status",tStatus);
        }

        resultMap.put("已审批",Yresult);
        return resultMap;
    }

    @Override
    public String save(Map<String,Object> map) {
        String  account = (String) map.get("account");
        Judges judges = judgeDao.selectById(account);
        JSONArray list = JSONArray.fromObject(map.get("table"));
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();

       // for (JudgesSave judgesSave : judgesSaveList) {
            String[] split = ob.getString("key").split("#");
            String year = split[0];
            String status = split[1];
            String leaderAccount = split[2];
            String grade = ob.getString("grade");
            String inf = ob.getString("inf");
            if (judges.getNumber().equals("one")) {
                judgeDao.updateOneGrade(leaderAccount, year, status, grade, inf);
            } else if (judges.getNumber().equals("two")) {
                judgeDao.updateTwoGrade(leaderAccount, year, status, grade, inf);
            } else if (judges.getNumber().equals("three")) {
                judgeDao.updateThreeGrade(leaderAccount, year, status, grade, inf);
            } else if (judges.getNumber().equals("four")) {
                judgeDao.updateFourGrade(leaderAccount, year, status, grade, inf);
                //todo 计算平均分
            } else {
                return "您已提交";
            }
            if(judges.getNumber().equals("one")){
                judgeDao.updateOneApply(leaderAccount, year, status, "已保存");
            }else if(judges.getNumber().equals("two")){
                judgeDao.updateTwoApply(leaderAccount, year, status, "已保存");
            }else if(judges.getNumber().equals("three")){
                judgeDao.updateThreeApply(leaderAccount, year, status, "已保存");
            }else if(judges.getNumber().equals("four")){
                judgeDao.updateFourApply(leaderAccount, year, status, "已保存");
            }
        }
        return "已成功保存";
    }

    @Override
    public String apply(Map<String,Object> map) {
        DecimalFormat df = new DecimalFormat("0.00");
        String  account = (String) map.get("account");
        Judges judges = judgeDao.selectById(account);
        JSONArray list = JSONArray.fromObject(map.get("table"));
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();
        //for (JudgesSave judgesSave : judgesSaveList) {
            String[] split = ob.getString("key").split("#");
            String year = split[0];
            String status = split[1];
            String leaderAccount = split[2];
            String grade = ob.getString("grade");
            String inf = ob.getString("inf");
            PGrade pGrade = judgeDao.selectByGId(leaderAccount, year, status);
            if (judges.getNumber().equals("one")) {
                judgeDao.updateOneGrade(leaderAccount, year, status, grade, inf);
            } else if (judges.getNumber().equals("two")) {
                judgeDao.updateTwoGrade(leaderAccount, year, status, grade, inf);
            } else if (judges.getNumber().equals("three")) {
                judgeDao.updateThreeGrade(leaderAccount, year, status, grade, inf);
            } else if (judges.getNumber().equals("four")) {
                judgeDao.updateFourGrade(leaderAccount, year, status, grade, inf);
                //todo 计算平均分
            } else {
                return "您已提交";
            }
            if(pGrade.getOneGrade() != 0 && pGrade.getTwoGrade() != 0 && pGrade.getTwoGrade() != 0 && pGrade.getFourGrade() != 0){
                int one = pGrade.getOneGrade();
                int two = pGrade.getTwoGrade();
                int three = pGrade.getThreeGrade();
                int four = pGrade.getFourGrade();
                Double avg = Double.valueOf(df.format((one + two + three + four) / 4));
                judgeDao.updateAvg(leaderAccount, year, status, avg);
            }
            if(judges.getNumber().equals("one")){
                judgeDao.updateOneApply(leaderAccount, year, status, "已提交");
            }else if(judges.getNumber().equals("two")){
                judgeDao.updateTwoApply(leaderAccount, year, status, "已提交");
            }else if(judges.getNumber().equals("three")){
                judgeDao.updateThreeApply(leaderAccount, year, status, "已提交");
            }else if(judges.getNumber().equals("four")){
                judgeDao.updateFourApply(leaderAccount, year, status, "已提交");
            }
        }
        return "已成功提交";
    }
}