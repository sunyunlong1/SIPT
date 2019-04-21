package com.scholarship.demo.api;

import lombok.Data;

@Data
public class CurrentProcessRep {

    private String processName;
    private String isCollect;
    private String startTime;
    private String endTime;
    private String key;
    private String keyTwo;
    private String middlePName;
    private String endPName;
}
