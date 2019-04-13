package com.scholarship.demo.api;

import java.util.List;
import java.util.Map;

public class ManagerTableDto {

    private UnifiedTable unifiedTable;
    private Map<String,List<ManagerDto>> managerDtoList;

    public UnifiedTable getUnifiedTable() {
        return unifiedTable;
    }

    public void setUnifiedTable(UnifiedTable unifiedTable) {
        this.unifiedTable = unifiedTable;
    }

    public Map<String, List<ManagerDto>> getManagerDtoList() {
        return managerDtoList;
    }

    public void setManagerDtoList(Map<String, List<ManagerDto>> managerDtoList) {
        this.managerDtoList = managerDtoList;
    }

}
