package com.scholarship.demo.api;

import lombok.Data;

public class StudentRequestDto {

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


    public StudentRequestDto(){

    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderCollege() {
        return leaderCollege;
    }

    public void setLeaderCollege(String leaderCollege) {
        this.leaderCollege = leaderCollege;
    }

    public String getLeaderAccount() {
        return leaderAccount;
    }

    public void setLeaderAccount(String leaderAccount) {
        this.leaderAccount = leaderAccount;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getMemberInf() {
        return memberInf;
    }

    public void setMemberInf(String memberInf) {
        this.memberInf = memberInf;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String getpSource() {
        return pSource;
    }

    public void setpSource(String pSource) {
        this.pSource = pSource;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpIntroduction() {
        return pIntroduction;
    }

    public void setpIntroduction(String pIntroduction) {
        this.pIntroduction = pIntroduction;
    }

    public String getPathFirst() {
        return pathFirst;
    }

    public void setPathFirst(String pathFirst) {
        this.pathFirst = pathFirst;
    }

    public String getPathSecond() {
        return pathSecond;
    }

    public void setPathSecond(String pathSecond) {
        this.pathSecond = pathSecond;
    }

    public String getPathThird() {
        return pathThird;
    }

    public void setPathThird(String pathThird) {
        this.pathThird = pathThird;
    }

    public StudentRequestDto(String pName, String pType, String leaderName, String leaderCollege, String leaderAccount, String memberNum, String memberInf, String teacherName, String teacherTitle, String pSource, String pCode, String pIntroduction, String pathFirst, String pathSecond, String pathThird) {
        this.pName = pName;
        this.pType = pType;
        this.leaderName = leaderName;
        this.leaderCollege = leaderCollege;
        this.leaderAccount = leaderAccount;
        this.memberNum = memberNum;
        this.memberInf = memberInf;
        this.teacherName = teacherName;
        this.teacherTitle = teacherTitle;
        this.pSource = pSource;
        this.pCode = pCode;
        this.pIntroduction = pIntroduction;
        this.pathFirst = pathFirst;
        this.pathSecond = pathSecond;
        this.pathThird = pathThird;
    }
}
