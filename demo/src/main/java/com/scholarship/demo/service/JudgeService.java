package com.scholarship.demo.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JudgeService {

    /**
     * 评委view 需要传账号 年份
     * @param jAccount
     * @param
     * @return
     */
    Map<String,Object> view(String jAccount);


    /**
     * key 年份+状态+学号
     * @param
     * @return
     */
    String save(Map<String,Object> map);

    /**
     * key 年份+状态+学号
     * @param
     * @return
     */
    String apply(Map<String,Object> map);
}
