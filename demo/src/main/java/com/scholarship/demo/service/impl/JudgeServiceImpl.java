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
            List<Project> projects = judgeDao.selectByCollege(judges.getCollege());
            for (Project project : projects) {
                PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                if(judges.getNumber().equals("one") && pGrade.getOneGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("two") && pGrade.getTwoGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("three") && pGrade.getThreeGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("four") && pGrade.getFourGrade() == -1){
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }
                resultList.add(judgeViewRep);
                judgeRep.setData(resultList);
            }
            judgeRep.setTitle(siptProcess.getYear() + siptProcess.getStatus());
            judgeRepList.add(judgeRep);
        }
        result.setNotApproval(judgeRepList);

        //已审批
        //Map<String, Object> Yresult = new HashMap<>();
        List<JudgeRep> YJudgeRepList = new ArrayList<>();
        for (SiptProcess siptProcess : siptProcessList) {
            JudgeRep YJudgeRep = new JudgeRep();
            List<JudgeViewRep> resultList = new ArrayList<>();
            List<Project> projects = judgeDao.selectByCollege(judges.getCollege());
            for (Project project : projects) {
                PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                JudgeViewRep judgeViewRep = new JudgeViewRep();
                if(judges.getNumber().equals("one") && pGrade.getOneGrade() != -1 && pGrade.getOneApply().equals("已提交")){
                    tStatus = pGrade.getOneApply();
                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getOneGrade()));
                    judgeViewRep.setInf(pGrade.getOneInf());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("two") && pGrade.getTwoGrade() != -1 && pGrade.getTwoApply().equals("已提交")){
                    tStatus = pGrade.getTwoApply();

                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getTwoGrade()));
                    judgeViewRep.setInf(pGrade.getTwoInf());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("three") && pGrade.getThreeGrade() != -1 && pGrade.getThreeApply().equals("已提交")){
                    tStatus = pGrade.getThreeApply();

                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getThreeGrade()));
                    judgeViewRep.setInf(pGrade.getThreeInf());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }else if(judges.getNumber().equals("four") && pGrade.getFourGrade() != -1 && pGrade.getFourApply().equals("已提交")){
                    tStatus = pGrade.getFourApply();


                    judgeViewRep.setPType(project.getPType());
                    judgeViewRep.setPName(project.getPName());
                    judgeViewRep.setGrade(String.valueOf(pGrade.getFourGrade()));
                    judgeViewRep.setInf(pGrade.getFourInf());
                    judgeViewRep.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                }
                resultList.add(judgeViewRep);
                YJudgeRep.setData(resultList);

            }
            YJudgeRep.setTitle(siptProcess.getYear() + siptProcess.getStatus());

            YJudgeRepList.add(YJudgeRep);
        }
        result.setIsApproval(YJudgeRepList);
        return result;
    }

    @Override
    public String save(JudgeRepList list) {


        String  account = list.getAccount();
        Judges judges = judgeDao.selectById(account);
        for(JudgeViewRep judgeViewRep : list.getData()){

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
    }

    @Override
    public String apply(JudgeRepList list) {
        DecimalFormat df = new DecimalFormat("0.00");

        String  account = list.getAccount();
        Judges judges = judgeDao.selectById(account);
        for (JudgeViewRep judgeViewRep : list.getData()){

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
                //todo 计算平均分
            } else {
                return "您已提交";
            }
            if(pGrade.getOneGrade() != -1 && pGrade.getTwoGrade() != -1 && pGrade.getTwoGrade() != -1 && pGrade.getFourGrade() != -1){
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