package com.scholarship.demo.api;

import lombok.Data;

@Data
public class CurrentProcessRep {

    private String processName;
    private String isCollect;
    private String startTime;
    private String endTime;
}
