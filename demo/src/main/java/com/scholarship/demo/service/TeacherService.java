package com.scholarship.demo.service;

import com.scholarship.demo.api.TeacherAppRep;
import com.scholarship.demo.api.TeacherMyProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    /**
     * 待审批
     * @param account 传账号，年份
     * @return
     */
    List<TeacherAppRep> pApproval(String account);

    /**
     * 指导教师提交 传leaderName，状态
     * @param key
     * @return
     */
    String approve(String key);

    /**
     * 指导教师提交 传leaderName，状态
     * @param key
     * @return
     */
    String notApprove(String key);


    /**
     * 我的项目 只传account
     * @param account
     * @return
     */
    List<TeacherMyProject> myProject(String account);
}
