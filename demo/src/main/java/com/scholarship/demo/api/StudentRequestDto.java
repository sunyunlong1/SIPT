package com.scholarship.demo.api;

import lombok.Data;

@Data
public class StudentRequestDto {

    /**
     * 当前年份
     */
    private String year;
    /**
     * 项目名称
     */
    private String pName;

    /**
     * 项目类型
     */
    private String pType;

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
     * 指导教师职称
     */
    private String teacherTitle;

    /**
     * 项目涞源
     */
    private String pSource;

    /**
     * 项目所属一级学科代码
     */
    private String pCode;

    /**
     * 项目简介
     */
    private String pIntroduction;

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
}
