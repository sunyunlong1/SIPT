package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;

@Data
public class JudgeResult {

    private List<JudgeRep> isApproval;
    private List<JudgeRep> notApproval;
}
