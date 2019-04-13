package com.scholarship.demo.model;

public class PingshenGrade {

    private int id;
    private int sId;
    private String pStatus;
    private int oneGrade;
    private int twoGrade;
    private int threeGrade;
    private int fourGrade;
    private double pgAvg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
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

    public int getFourGrade() {
        return fourGrade;
    }

    public void setFourGrade(int fourGrade) {
        this.fourGrade = fourGrade;
    }

    public double getAvg() {
        return pgAvg;
    }

    public void setAvg(double pgAvg) {
        this.pgAvg = pgAvg;
    }
}