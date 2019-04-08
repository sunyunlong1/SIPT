package com.scholarship.demo.model;

public class Grade {

    private int id;
    private int pId;
    private String tAccount;
    private String fGrade;
    private String sGrade;
    private String tGrade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String gettAccount() {
        return tAccount;
    }

    public void settAccount(String tAccount) {
        this.tAccount = tAccount;
    }

    public String getfGrade() {
        return fGrade;
    }

    public void setfGrade(String fGrade) {
        this.fGrade = fGrade;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }

    public String gettGrade() {
        return tGrade;
    }

    public void settGrade(String tGrade) {
        this.tGrade = tGrade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", pId=" + pId +
                ", tAccount='" + tAccount + '\'' +
                ", fGrade='" + fGrade + '\'' +
                ", sGrade='" + sGrade + '\'' +
                ", tGrade='" + tGrade + '\'' +
                '}';
    }
}
