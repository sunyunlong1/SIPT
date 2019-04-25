package com.scholarship.demo.service.impl;

import com.scholarship.demo.api.*;
import com.scholarship.demo.dao.JudgeDao;
import com.scholarship.demo.dao.ManagerDao;
import com.scholarship.demo.model.*;
import com.scholarship.demo.service.JudgeService;
import com.scholarship.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;
    @Autowired
    JudgeService judgeService;
    @Autowired
    JudgeDao judgeDao;

    @Override
    public ManagerTableDto currentProcess(String account) {
        //int index = 0;
        ManagerTableDto result = new ManagerTableDto();
        List<UnifiedTable> unifiedTables = new ArrayList<>();
        Admin admin = managerDao.selectById(account);
        List<SiptProcess> siptProcessList = managerDao.selectByConduct("流程中");
        if (siptProcessList.size() == 0) {
            return null;
        } else {
            for (SiptProcess siptProcess : siptProcessList) {
                Map<String, List<ManagerDto>> resultMap = new HashMap<>();
                UnifiedTable unifiedTable = new UnifiedTable();
                List<Project> projects = managerDao.selectBySidYear(admin.getCollege(), siptProcess.getYear(),"已保存","pass");
                List<ManagerDto> managerDtos = new ArrayList<>();
                for (Project project : projects) {
                    ManagerDto managerDto = new ManagerDto();
                    managerDto.setCollege(project.getCollege());
                    managerDto.setLeaderName(project.getSName());
                    managerDto.setLeaderAccount(project.getSAccount());
                    managerDto.setTName(project.getTName());
                    managerDto.setPName(project.getPName());
                    managerDto.setPType(project.getPType());

                    managerDto.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
                    PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(), siptProcess.getYear(), siptProcess.getStatus());
                    if(pGrade != null){
                        if (admin.getLevel().equals("校级")){
                            managerDto.setYep(pGrade.getCLevel());
                        }
                        managerDto.setOneGrade(pGrade.getOneGrade() == -1 ? "-" : String.valueOf(pGrade.getOneGrade()));
                        managerDto.setTwoGrade(pGrade.getTwoGrade() == -1 ? "-" : String.valueOf(pGrade.getTwoGrade()));
                        managerDto.setThreeGrade(pGrade.getThreeGrade() == -1 ? "-" : String.valueOf(pGrade.getThreeGrade()));
                        managerDto.setFourGrade(pGrade.getFourGrade() == -1 ? "-" :String.valueOf(pGrade.getFourGrade()));
                        managerDto.setPgAvg(pGrade.getPgAvg() == 0.00 ? "-" :String.valueOf(pGrade.getPgAvg()));
                    }
                    managerDtos.add(managerDto);

                }
                unifiedTable.setKey(siptProcess.getYear()+"::"+siptProcess.getStatus());
                resultMap.put(siptProcess.getYear() + siptProcess.getStatus(), managerDtos);
                unifiedTable.setManagerDtoList(resultMap);


                unifiedTable.setLevel(admin.getLevel());
//                if (siptProcessList.size() == 1) {
////                    unifiedTable.setCurrentProcess(siptProcessList.get(0).getYear() + siptProcessList.get(0).getStatus());
////                } else if (siptProcessList.size() == 2) {
////                    Integer year = Integer.valueOf(siptProcessList.get(0).getYear());
////                    Integer nYear = Integer.valueOf(siptProcessList.get(1).getYear());
////                    if (year > nYear) {
////                        unifiedTable.setCurrentProcess(siptProcessList.get(0).getYear() + siptProcessList.get(0).getStatus() + "/" + siptProcessList.get(1).getYear() + siptProcessList.get(1).getStatus());
////                    } else {
////                        unifiedTable.setCurrentProcess(siptProcessList.get(1).getYear() + siptProcessList.get(1).getStatus() + "/" + siptProcessList.get(0).getYear() + siptProcessList.get(0).getStatus());
////                    }
////                }
                unifiedTable.setCurrentProcess(siptProcess.getYear()+siptProcess.getStatus());

                if (admin.getLevel().equals("院级")) {
                    if (siptProcess.getIsCollect().equals("收取材料")) {
                        unifiedTable.setState(siptProcess.getIsCollect());
                    }else if(!admin.getIsApply().equals("-")){
                        unifiedTable.setState(admin.getIsApply());
                    } else if (siptProcess.getIsCollect().equals("正在审批")) {
                        int index = 0;
                        List<Judges> judgeList = managerDao.selectByJAccount(admin.getCollege());
                        for (Judges judge : judgeList){
                            int num = 0;
                            List<Project> Judgeproject = judgeDao.selectByCollege(judge.getCollege());
                            for(Project project : Judgeproject){
                                PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
                                if (judge.getNumber().equals("one") && pGrade.getOneGrade() == -1) {
                                    num++;
                                } else if (judge.getNumber().equals("two") && pGrade.getTwoGrade() == -1) {
                                    num++;
                                } else if (judge.getNumber().equals("three") && pGrade.getThreeGrade() == -1) {
                                    num++;
                                } else if (judge.getNumber().equals("four") && pGrade.getFourGrade() == -1) {
                                    num++;
                                }
                            }
                            if(num == 0){
                                index++;
                            }
                        }
                        if(index == 4){
                            unifiedTable.setState("待提交");
                        }else{
                            unifiedTable.setState("正在审批("+index+"/4)");
                        }
                    }
                }else{
                        if (siptProcess.getIsCollect().equals("收集材料")) {
                            unifiedTable.setState(siptProcess.getIsCollect());
                        }else if(siptProcess.getIsCollect().equals("正在审批")){
                            List<Admin> admins = managerDao.selectAllIsApply("院级");
                            if(admins.size() == 0){
                                unifiedTable.setState("待提交");
                                managerDao.UpdateCollect("待提交",siptProcess.getYear());
                            }else{
                                unifiedTable.setState(siptProcess.getIsCollect());
                            }
                        }else{
                            unifiedTable.setState(siptProcess.getIsCollect());
                        }
                    }
                unifiedTables.add(unifiedTable);
            }
            result.setUnifiedTable(unifiedTables);

        }
        return result;
    }

    @Override
    public String apply(Map<String, List<Key>> keyMap) {
        String year = "";
        String account = "";
        Set<String> strings = keyMap.keySet();
        List<Key> keyList = null;
        for (String s : strings) {
            keyList = keyMap.get(s);
            account = s;
        }
        Admin admin = managerDao.selectById(account);
        if (admin.getLevel().equals("校级")) {
            for (Key key : keyList) {
                String[] split = key.getKey().split("::");
                year = split[0];
                PGrade pGrade = managerDao.selectLevel(split[2], split[0], split[1]);
                if (pGrade.getLevel() == null || !pGrade.getLevel().equals("")){
                    managerDao.UpdatePGradeLevel(split[2], split[0], split[1], key.getLevel());
                }
                managerDao.UpdateProjectTApproval(split[2],split[0],"","","已保存");
                List<Admin> adminList = managerDao.selectByAdmin("校级");
                if(adminList!= null){
                    for (Admin admin1 : adminList){
                        managerDao.updateApply(admin1.getAccount(),"-");
                    }
                }
                managerDao.UpdateConduct(year, "流程结束");
                managerDao.UpdateCollect("已提交", year);
            }
        } else {
            for (Key key : keyList) {
                String[] split = key.getKey().split("::");
                PGrade pGrade = managerDao.selectLevel(split[2], split[0], split[1]);
                if (pGrade.getCLevel() == null || !pGrade.getLevel().equals("")){
                    managerDao.UpdatePGradeCLevel(split[2], split[0], split[1], key.getLevel());
                }
                managerDao.updateApply(account, "已提交");
            }

        }
        return "提交结果成功";
    }

    @Override
    public String stop(String name) {
        //String year = name.substring(0, 4);
        //String status = name.substring(4, name.length());

        String[] split = name.split("::");
        String year = split[0];
        String status = split[1];

        SiptProcess siptProcess = managerDao.selectByYear(year);
        if (!siptProcess.getIsCollect().equals("收取材料")) {
            return "fail";
        } else {
            managerDao.UpdatePGradeCollect(year, status, "正在审批");
            return "success";
        }
    }

    @Override
    public OverViewDto overview(String account) {
        OverViewDto result = new OverViewDto();
        List<OverviewResponse> resultList = new ArrayList<>();
        Admin admin = managerDao.selectById(account);
        List<SiptProcess> siptProcesses = managerDao.selectProcess();
        for (SiptProcess siptProcess : siptProcesses) {
            OverviewResponse overviewResponse = new OverviewResponse();
            Integer integer = managerDao.selectSumProject(siptProcess.getYear(), admin.getCollege());
            overviewResponse.setName(siptProcess.getYear() + "SIPT");
            overviewResponse.setSum(integer);
            overviewResponse.setPStatus(siptProcess.getStatus());
            overviewResponse.setIsCollect(siptProcess.getIsCollect());
            overviewResponse.setKey(siptProcess.getYear() + "::" + siptProcess.getStatus());
            resultList.add(overviewResponse);
        }
        result.setData(resultList);
        result.setLevel(admin.getLevel());
        return result;
    }

    @Override
    public List<ManagerViewProject> details(Key key) {
        String[] split = key.getKey().split("::");
        List<ManagerViewProject> result = new ArrayList<>();
        String year = split[0];
        SiptProcess siptProcess = managerDao.selectByYear(year);
        List<Project> projects = managerDao.selectProjectByYear(year);
        for (Project project : projects) {
            ManagerViewProject managerViewProject = new ManagerViewProject();
            PGrade pGrade = managerDao.selectByIdYStatus(project.getSAccount(), project.getYear(), siptProcess.getStatus());
            if (pGrade == null || pGrade.getLevel() == null || pGrade.getLevel().equals("")) {
                managerViewProject.setAvg("-");
            } else {
                managerViewProject.setAvg(pGrade.getLevel());

            }
            managerViewProject.setTeacherName(project.getTName());
            managerViewProject.setCollege(project.getCollege());
            managerViewProject.setLeaderUserName(project.getSName());
            managerViewProject.setPName(project.getPName());
            managerViewProject.setPSource(project.getPSource());
            managerViewProject.setKey(project.getYear() + "::" + siptProcess.getStatus() + "::" + project.getSAccount());
            result.add(managerViewProject);
        }
        return result;
    }

    @Override
    public String newAndEditProcess(NewProcessDto newProcessDto) {
        //立项 begin年份+1
        String year = newProcessDto.getBeginTime().substring(0, 4);
        if (newProcessDto.getProcessName().equals("立项")) {

            Integer LYear = Integer.valueOf(year) + 1;
            SiptProcess siptProcess = managerDao.selectByYear(LYear.toString());
            if(siptProcess != null){
                if (siptProcess.getStatus().equals("立项")) {
                    return "已存在当前流程，不可重复新建";
                }
            }
            Integer integer = managerDao.insertProcess(LYear.toString(), newProcessDto.getProcessName(), newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料", "流程中");

        } else {
            String[] split = newProcessDto.getProcessName().split("/");
            Integer JYear = Integer.valueOf(year) - 1;
            SiptProcess siptProcess = managerDao.selectByYear(year);
            SiptProcess YsiptProcess = managerDao.selectByYear(JYear.toString());
            if (siptProcess.getStatus().equals("中期检查") || YsiptProcess.equals("结项")) {
                return "已存在当前流程，不可重复新建";
            }
            managerDao.updateProcess(year, split[0], newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料", "流程中");
            managerDao.updateProcess(JYear.toString(), split[1], newProcessDto.getBeginTime(), newProcessDto.getEndTime(), "收取材料", "流程中");
        }
        return "成功新建流程";
    }


//    public List<String> findNum(String college) {
//        Integer index = 0;
//        List<Judges> judgeList = managerDao.selectByJAccount(college);
//        String s = "";
//        List<SiptProcess> siptProcessList = judgeDao.selectByConduct("流程中");
//        List<String> result = new ArrayList<>();
//        for (SiptProcess siptProcess : siptProcessList) {
//            for (Judges judge : judgeList) {
//                List<JudgeViewRep> resultList = new ArrayList<>();
//                List<Project> projects = judgeDao.selectByCollege(judge.getCollege());
//                for (Project project : projects) {
//                    PGrade pGrade = judgeDao.selectByGId(project.getSAccount(), project.getYear(), siptProcess.getStatus());
//                    JudgeViewRep judgeViewRep = new JudgeViewRep();
//                    if (judge.getNumber().equals("one") && pGrade.getOneGrade() == -1) {
//                        resultList.add(judgeViewRep);
//                    } else if (judge.getNumber().equals("two") && pGrade.getTwoGrade() == -1) {
//                        resultList.add(judgeViewRep);
//                    } else if (judge.getNumber().equals("three") && pGrade.getThreeGrade() == -1) {
//                        resultList.add(judgeViewRep);
//                    } else if (judge.getNumber().equals("four") && pGrade.getFourGrade() == -1) {
//                        resultList.add(judgeViewRep);
//                    }
//                }
//                if(resultList.size() == 0){
//                    index++;
//                }
//            }
//            s = siptProcess.getStatus()+"::"+index;
//            result.add(s);
//        }
//        return result;
//    }
}
