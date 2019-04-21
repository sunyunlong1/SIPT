package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ManagerTableDto {

    private List<UnifiedTable> unifiedTable;
    private Map<String,List<ManagerDto>> managerDtoList;

}
