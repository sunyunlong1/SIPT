package com.scholarship.demo.model;

import java.util.Date;

public class Project {

    private int id;
    private String pId;
    private String pName;
    private String pAccount;
    private int menberNum;
    private String menberInf;
    private String tAccount;
    private String pSource;
    private String pCode;
    private String pIntroduction;
    private String pStatus;
    private String years;
    private String pathFirst;
    private String pathSecond;
    private String pathThird;
    private String avg;
    private String demandPath;
    private Date lastTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAccount() {
        return pAccount;
    }

    public void setpAccount(String pAccount) {
        this.pAccount = pAccount;
    }

    public int getMenberNum() {
        return menberNum;
    }

    public void setMenberNum(int menberNum) {
        this.menberNum = menberNum;
    }

    public String getMenberInf() {
        return menberInf;
    }

    public void setMenberInf(String menberInf) {
        this.menberInf = menberInf;
    }

    public String gettAccount() {
        return tAccount;
    }

    public void settAccount(String tAccount) {
        this.tAccount = tAccount;
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

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
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

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getDemandPath() {
        return demandPath;
    }

    public void setDemandPath(String demandPath) {
        this.demandPath = demandPath;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", pId='" + pId + '\'' +
                ", pName='" + pName + '\'' +
                ", pAccount='" + pAccount + '\'' +
                ", menberNum=" + menberNum +
                ", menberInf='" + menberInf + '\'' +
                ", tAccount='" + tAccount + '\'' +
                ", pSource='" + pSource + '\'' +
                ", pCode='" + pCode + '\'' +
                ", pIntroduction='" + pIntroduction + '\'' +
                ", pStatus='" + pStatus + '\'' +
                ", years='" + years + '\'' +
                ", pathFirst='" + pathFirst + '\'' +
                ", pathSecond='" + pathSecond + '\'' +
                ", pathThird='" + pathThird + '\'' +
                ", avg='" + avg + '\'' +
                ", demandPath='" + demandPath + '\'' +
                ", lastTime=" + lastTime +
                '}';
    }
}
