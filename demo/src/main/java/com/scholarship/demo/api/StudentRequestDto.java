package com.scholarship.demo.api;

import lombok.Data;

@Data
public class StudentRequestDto {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 项目负责人姓名
     */
    private String leaderName;

    /**
     * 负责人所在学院
     */
    private String leaderCollege;

    /**
     * 负责人学号
     */
    private String leaderAccount;

    /**
     * 参与人数
     */
    private String memberNum;

    /**
     * 其他成员信息
     */
    private String memberInf;

    /**
     * 指导教师姓名
     */
    private String teacherName;

    /**
     * 指导教师工号
     */
    private String account;

    /**
     * 项目涞源
     */
    private String source;

    /**
     * 项目所属一级学科代码
     */
    private String code;

    /**
     * 项目简介
     */
    private String introduction;

    /**
     * 立项上传文件
     */
    private String pathFirst;

    /**
     * 中期检查上传文件
     */
    private String pathSecond;

    /**
     *  结题上传文件
     */
    private String pathThird;
    /**
     * key
     */
    private String key;
}
