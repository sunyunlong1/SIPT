package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UnifiedTable {

    private String currentProcess;
    private String level;
    private String state;
    private Map<String, List<ManagerDto>> managerDtoList;
}
