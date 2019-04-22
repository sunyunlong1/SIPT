package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CurrentProcessRep {

    private String processName;
    private String isCollect;
    private String startTime;
    private String endTime;
    private String key;
    private List<KeyUser> projectList;
}
