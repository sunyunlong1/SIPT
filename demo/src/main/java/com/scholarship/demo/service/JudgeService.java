package com.scholarship.demo.service;

import com.scholarship.demo.api.JudgeRepList;
import com.scholarship.demo.api.JudgeResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface JudgeService {

    /**
     * 评委view 需要传账号 年份
     * @param jAccount
     * @param
     * @return
     */
    JudgeResult view(String jAccount);


    /**
     * key 年份+状态+学号
     * @param
     * @return
     */
    String save(JudgeRepList list);

    /**
     * key 年份+状态+学号
     * @param
     * @return
     */
    String apply(JudgeRepList list);
}
