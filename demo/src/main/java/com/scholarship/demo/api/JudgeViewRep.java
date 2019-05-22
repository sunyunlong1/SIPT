package com.scholarship.demo.api;

import lombok.Data;

@Data
public class JudgeViewRep {

    private String pName;
    private String pType;
    private String path;
    private String grade;
    private String inf;
    private String key;

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }
}
