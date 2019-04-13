package com.scholarship.demo.api;

public class ManagerDto {

    private String college;
    private String userName;
    private String tName;
    private String pType;
    private int oneGrade;
    private int twoGrade;
    private int threeGrade;
    private int fourGrade;
    private double pgAvg;
    private String level;

    public int getFourGrade() {
        return fourGrade;
    }

    public void setFourGrade(int fourGrade) {
        this.fourGrade = fourGrade;
    }

    public double getPgAvg() {
        return pgAvg;
    }

    public void setPgAvg(double pgAvg) {
        this.pgAvg = pgAvg;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public int getOneGrade() {
        return oneGrade;
    }

    public void setOneGrade(int oneGrade) {
        this.oneGrade = oneGrade;
    }

    public int getTwoGrade() {
        return twoGrade;
    }

    public void setTwoGrade(int twoGrade) {
        this.twoGrade = twoGrade;
    }

    public int getThreeGrade() {
        return threeGrade;
    }

    public void setThreeGrade(int threeGrade) {
        this.threeGrade = threeGrade;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
