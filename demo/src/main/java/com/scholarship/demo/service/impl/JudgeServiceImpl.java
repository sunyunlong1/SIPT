package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.JudgeRep;
import com.scholarship.demo.api.JudgeRepList;
import com.scholarship.demo.api.JudgeResult;
import com.scholarship.demo.api.JudgeViewRep;
import com.scholarship.demo.dao.JudgeDao;
import com.scholarship.demo.model.Judges;
import com.scholarship.demo.model.PGrade;
import com.scholarship.demo.model.Project;
import com.scholarship.demo.model.SiptProcess;
import com.scholarship.demo.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    JudgeDao judgeDao;

    @Override
    public JudgeResult view(String jAccount) {
        //List<JudgeResult> resultLIst = new ArrayList<>();
        JudgeResult result = new JudgeResult();

        List<JudgeRep> judgeRepList = new ArrayList<>();
        String oStatus = "";
        String tStatus = "";
        Judges judges = judgeDao.selectById(jAccount);
        List<SiptProcess> siptProcessList = judgeDao.selectByConduct("正在审批","流程中");
        for (SiptProcess siptProcess : siptProcessList) {
            JudgeRep judgeRep = new JudgeRep();
            List<JudgeViewRep> resultList = new ArrayList<>();
            List<Project> projects = judgeDao.selectByCollege(judges.getCollege(),siptProcess.getYear());
            for (Project project : projects) {
                PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                if(judges.getNumber().equals("one") && pGrade.getOneApply().equals("")){
                    judgeViewRep.setpType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("two") && pGrade.getTwoApply().equals("")){
                    judgeViewRep.setpType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("three") && pGrade.getThreeApply().equals("")){
                    judgeViewRep.setpType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("four") && pGrade.getFourApply().equals("")){
                    judgeViewRep.setpType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }
                if (judgeViewRep != null){
                    resultList.add(judgeViewRep);
                }
                if (resultList !=null){
                    judgeRep.setData(resultList);
                }
            }
            judgeRep.setTitle(siptProcess.getYear() + siptProcess.getStatus());
            if (judgeRep != null){
                judgeRepList.add(judgeRep);
            }
        }
        result.setNotApproval(judgeRepList);

        //已审批
        //Map<String, Object> Yresult = new HashMap<>();
        List<JudgeRep> YJudgeRepList = new ArrayList<>();
        for (SiptProcess siptProcess : siptProcessList) {
            JudgeRep YJudgeRep = new JudgeRep();
            List<JudgeViewRep> resultList = new ArrayList<>();
            List<Project> projects = judgeDao.selectByCollege(judges.getCollege(),siptProcess.getYear());
            for (Project project : projects) {
                PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                if(judges.getNumber().equals("one") && pGrade.getOneGrade() != -1 && !pGrade.getOneApply().equals("")){
                    tStatus = pGrade.getOneApply();
                    judgeViewRep.setpType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getOneGrade()));
                    judgeViewRep.setInf(pGrade.getOneInf());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("two") && pGrade.getTwoGrade() != -1 && !pGrade.getOneApply().equals("")){
                    tStatus = pGrade.getTwoApply();

                    judgeViewRep.setpType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getTwoGrade()));
                    judgeViewRep.setInf(pGrade.getTwoInf());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("three") && pGrade.getThreeGrade() != -1 && !pGrade.getOneApply().equals("")){
                    tStatus = pGrade.getThreeApply();

                    judgeViewRep.setpType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getThreeGrade()));
                    judgeViewRep.setInf(pGrade.getThreeInf());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("four") && pGrade.getFourGrade() != -1 && !pGrade.getOneApply().equals("")){
                    tStatus = pGrade.getFourApply();

                    if(project != null){
                        judgeViewRep.setpType(project.getPType());
                        judgeViewRep.setPName(project.getPName());
                        judgeViewRep.setGrade(String.valueOf(pGrade.getFourGrade()));
                        judgeViewRep.setInf(pGrade.getFourInf());
                        judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                    }
                }
                if (judgeViewRep!=null && judgeViewRep.getpType() != null){
                    resultList.add(judgeViewRep);
                }
                if (resultList !=null){
                    YJudgeRep.setData(resultList);
                }
            }
            YJudgeRep.setTitle(siptProcess.getYear() + siptProcess.getStatus());
            if (YJudgeRep != null){
                YJudgeRepList.add(YJudgeRep);
            }
        }
        result.setIsApproval(YJudgeRepList);
        return result;
    }

    @Override
    public String save(JudgeRepList list) {


        String  account = list.getAccount();
        Judges judges = judgeDao.selectById(account);
        List<JudgeViewRep> data = list.getData();
        if (data != null){
            for(JudgeViewRep judgeViewRep : data){

                // for (JudgesSave judgesSave : judgesSaveList) {
                String[] split = judgeViewRep.getKey().split("::");
                String year = split[0];
                String status = split[1];
                String leaderAccount = split[2];
                String grade = judgeViewRep.getGrade();
                String inf = judgeViewRep.getInf();
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
        }else{
            return "未成功保存";
        }


    }

    @Override
    public String apply(JudgeRepList list) {
        DecimalFormat df = new DecimalFormat("0.00");

        String  account = list.getAccount();
        Judges judges = judgeDao.selectById(account);
        List<JudgeViewRep> data = list.getData();
        if (data != null){
            for (JudgeViewRep judgeViewRep : data){

                String[] split = judgeViewRep.getKey().split("::");
                String year = split[0];
                String status = split[1];
                String leaderAccount = split[2];
                String grade = judgeViewRep.getGrade();
                String inf = judgeViewRep.getInf();
                PGrade pGrade = judgeDao.selectByGId(leaderAccount, year, status);
                if (judges.getNumber().equals("one")) {
                    judgeDao.updateOneGrade(leaderAccount, year, status, grade, inf);
                } else if (judges.getNumber().equals("two")) {
                    judgeDao.updateTwoGrade(leaderAccount, year, status, grade, inf);
                } else if (judges.getNumber().equals("three")) {
                    judgeDao.updateThreeGrade(leaderAccount, year, status, grade, inf);
                } else if (judges.getNumber().equals("four")) {
                    judgeDao.updateFourGrade(leaderAccount, year, status, grade, inf);
                }
                PGrade newpGrade = judgeDao.selectByGId(leaderAccount, year, status);

                if(newpGrade.getOneGrade() != -1 && newpGrade.getTwoGrade() != -1 && newpGrade.getTwoGrade() != -1 && newpGrade.getFourGrade() != -1){
                    int one = newpGrade.getOneGrade();
                    int two = newpGrade.getTwoGrade();
                    int three = newpGrade.getThreeGrade();
                    int four = newpGrade.getFourGrade();
                    Double avg = Double.valueOf(df.format((Double.valueOf(one) + Double.valueOf(two) + Double.valueOf(three) + Double.valueOf(four)) / 4));
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
        }else{
            return "未成功提交";
        }

    }
}