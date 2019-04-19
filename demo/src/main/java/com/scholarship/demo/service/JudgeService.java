package com.scholarship.demo.service;

import com.scholarship.demo.api.JudgeViewRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface JudgeService {

    /**
     * 评委view 需要传账号 年份
     * @param jAccount
     * @param year
     * @return
     */
    Map<String, List<JudgeViewRep>> view(String jAccount, String year);
}
