package com.scholarship.demo.api;

import lombok.Data;

import java.util.Map;

@Data
public class OverviewResponse {

    private String name;
    private int sum;
    private String pStatus;
    private String isCollect;
    private String key;
    private String level;
    private Map<String,Integer> sipt;
}
