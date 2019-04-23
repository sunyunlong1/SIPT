package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;

@Data
public class JudgeRepList {
    private String account;
    private List<JudgeViewRep> data;
}
