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
    private int twoGrade;
    private int threeGrade;
    private int fourGrade;
    private double pgAvg;
    private String level;
    private String cLevel;
}