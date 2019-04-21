package com.scholarship.demo.model;

import lombok.Data;

@Data
public class PGrade {

    private int id;
    private String sId;
    private String sName;
    private String year;
    private String pStatus;
    private int oneGrade;
    private String oneInf;
    private String oneApply;
    private int twoGrade;
    private String twoInf;
    private String twoApply;
    private int threeGrade;
    private String threeInf;
    private String threeApply;
    private int fourGrade;
    private String fourInf;
    private String fourApply;
    private double pgAvg;
    private String level;
    private String cLevel;
}