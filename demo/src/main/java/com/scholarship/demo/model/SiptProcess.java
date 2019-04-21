package com.scholarship.demo.model;

import lombok.Data;

@Data
public class SiptProcess {

    private int id;
    private String year;
    private String status;
    private String startTime;
    private String endTime;
    private String isCollect;
    private String isConduct;
}
